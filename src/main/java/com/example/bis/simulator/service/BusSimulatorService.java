package com.example.bis.simulator.service;

import com.example.bis.simulator.dto.BusDataResponse;
import com.example.bis.simulator.dto.BusSimulationResponse;
import com.example.bis.simulator.dto.RouteData;
import com.example.bis.simulator.model.C_TC_BUS_RUNG;
import com.example.bis.simulator.model.M_OP_BUS;
import com.example.bis.simulator.model.M_OP_OBU;
import com.example.bis.simulator.model.M_TP_BSTP;
import com.example.bis.simulator.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 버스 시뮬레이터 서비스 클래스.
 */
@Service
@RequiredArgsConstructor
public class BusSimulatorService {

    private final BusRepository busRepository;
    private final ObuRepository obuRepository;
    private final BusStopRepository busStopRepository;
    private final BusRungRepository busRungRepository;
    private final RouteService routeService;

    /**
     * 상위 10개의 버스를 조회합니다.
     */
    public List<BusDataResponse> getTopBuses() {
        List<M_OP_BUS> buses = busRepository.findAll(PageRequest.of(0, 10)).getContent();
        return buses.stream()
                .map(bus -> {
                    M_OP_OBU obu = obuRepository.findByBusId(bus.getBusId());
                    return new BusDataResponse(
                            bus.getBusId(),
                            bus.getBusNo(),
                            obu != null ? obu.getObuId() : null
                    );
                })
                .collect(Collectors.toList());
    }

    /**
     * 특정 OBU ID와 노선으로 시뮬레이터를 시작합니다.
     *
     * @param busId 버스 ID
     * @param routeId 노선 ID
     */
    public void startSimulation(String busId, String routeId) {
        if (busId == null || routeId == null) {
            throw new IllegalArgumentException("버스 ID와 노선 ID는 필수입니다.");
        }

        List<RouteData> routes = routeService.getOptimizedRouteData();
        RouteData selectedRoute = routes.stream()
                .filter(route -> route.getRouteId().equals(routeId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 노선을 찾을 수 없습니다: " + routeId));

        M_OP_OBU obu = obuRepository.findByBusId(Integer.parseInt(busId));
        if (obu == null) {
            throw new IllegalArgumentException("해당 버스를 찾을 수 없습니다: " + busId);
        }

        BigDecimal initialX = selectedRoute.getBusStops().get(0).getXcord();
        BigDecimal initialY = selectedRoute.getBusStops().get(0).getYcord();
        String arrivalPointId = selectedRoute.getBusStops().get(0).getBusStopId();

        initializeBusPosition(obu.getObuId(), initialX, initialY, arrivalPointId);
    }


    /**
     * 특정 OBU ID의 버스를 초기화합니다.
     */
    public BusSimulationResponse initializeBusPosition(String obuId, BigDecimal xCord, BigDecimal yCord, String arrivalPointId) {
        busRungRepository.deleteById(obuId);

        C_TC_BUS_RUNG busData = new C_TC_BUS_RUNG();
        busData.setObuId(obuId);
        busData.setXCord(xCord);
        busData.setYCord(yCord);
        busData.setArrivalPlannedPointId(arrivalPointId);
        busData.setPointPassageDate(LocalDateTime.now());
        busData.setBusLocationDivision("01");

        busRungRepository.save(busData);
        return mapToSimulationResponse(busData);
    }

    /**
     * 모든 버스를 초기화합니다.
     */
    public void initializeAllBuses(List<String> obuIds) {
        List<RouteData> routes = routeService.getOptimizedRouteData();

        for (int i = 0; i < obuIds.size(); i++) {
            String obuId = obuIds.get(i);
            RouteData route = routes.get(i % routes.size());

            BigDecimal initialX = route.getBusStops().get(0).getXcord();
            BigDecimal initialY = route.getBusStops().get(0).getYcord();
            String arrivalPointId = route.getBusStops().get(0).getBusStopId();

            initializeBusPosition(obuId, initialX, initialY, arrivalPointId);
        }
    }

    /**
     * 모든 버스를 5초마다 갱신합니다.
     */
    @Scheduled(fixedRate = 5000)
    public void updateAllSimulations() {
        List<C_TC_BUS_RUNG> buses = busRungRepository.findAll();
        for (C_TC_BUS_RUNG bus : buses) {
            updateSimulation(bus.getObuId());
        }
    }

    /**
     * 특정 OBU ID의 상태를 갱신합니다.
     */
    public BusSimulationResponse updateSimulation(String obuId) {
        C_TC_BUS_RUNG busData = busRungRepository.findById(obuId)
                .orElseThrow(() -> new RuntimeException("해당 OBU ID의 데이터가 존재하지 않습니다."));

        BigDecimal oldX = busData.getXCord();
        BigDecimal oldY = busData.getYCord();
        BigDecimal newX = oldX.add(BigDecimal.valueOf(0.0001));
        BigDecimal newY = oldY.add(BigDecimal.valueOf(0.0001));

        busData.setXCord(newX);
        busData.setYCord(newY);

        M_TP_BSTP arrivalStop = busStopRepository.findByBstpIds(List.of(busData.getArrivalPlannedPointId())).get(0);
        BigDecimal distance = calculateDistance(newX, newY, arrivalStop.getXcord(), arrivalStop.getYcord());

        if (distance.compareTo(BigDecimal.valueOf(arrivalStop.getEntJdgDist())) < 0) {
            busData.setBusLocationDivision("02");
        } else if (distance.compareTo(BigDecimal.valueOf(arrivalStop.getExtJdgDist())) > 0) {
            busData.setBusLocationDivision("03");
        } else {
            busData.setBusLocationDivision("01");
        }

        busRungRepository.save(busData);
        return mapToSimulationResponse(busData);
    }

    private BigDecimal calculateDistance(BigDecimal x1, BigDecimal y1, BigDecimal x2, BigDecimal y2) {
        return BigDecimal.valueOf(Math.sqrt(Math.pow(x1.subtract(x2).doubleValue(), 2) + Math.pow(y1.subtract(y2).doubleValue(), 2)));
    }

    private BusSimulationResponse mapToSimulationResponse(C_TC_BUS_RUNG busData) {
        return new BusSimulationResponse(
                busData.getObuId(),
                busData.getXCord(),
                busData.getYCord(),
                busData.getMomentSpeed(),
                busData.getPassagePointId(),
                busData.getPassagePointSqNo(),
                busData.getPointPassageDate(),
                busData.getArrivalPlannedPointId(),
                busData.getArrivalPlannedPointSqNo(),
                busData.getBusLocationDivision()
        );
    }
}
