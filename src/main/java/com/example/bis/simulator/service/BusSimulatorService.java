package com.example.bis.simulator.service;

import com.example.bis.simulator.dto.BusDataResponse;
import com.example.bis.simulator.dto.BusSimulationResponse;
import com.example.bis.simulator.dto.VertexDTO;
import com.example.bis.simulator.model.C_TC_BUS_RUNG;
import com.example.bis.simulator.repository.BusRungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusSimulatorService {

    private final BusRungRepository busRungRepository;
    private final RouteService routeService;

    @Value("${simulator.update.interval:5000}")
    private long updateInterval;

    /**
     * 특정 노선에 해당하는 버스 데이터를 조회
     *
     * @param routeId 노선 ID
     * @return 버스 데이터 리스트
     */
    public List<BusDataResponse> getBusesByRouteId(String routeId) {
        return busRungRepository.findBusDetailsByRouteId(routeId);
    }

    /**
     * 특정 노선에 해당하는 실시간 버스 위치 데이터 조회
     *
     * @param routeId 노선 ID
     * @return 실시간 위치 데이터 리스트
     */
    public List<BusSimulationResponse> getBusLocationsByRouteId(String routeId) {
        return busRungRepository.findBusLocationsByRouteId(routeId);
    }

    /**
     * 시뮬레이터 시작: 특정 OBU ID의 상태를 활성화(1)로 설정
     *
     * @param obuId OBU ID
     */
    @Transactional
    public void startSimulation(String obuId) {
        busRungRepository.updateBusStatus(obuId);
    }

    /**
     * 5초마다 버스 위치를 업데이트
     */
    @Scheduled(fixedRateString = "${simulator.update.interval:5000}")
    public void updateBusPositions() {
        List<C_TC_BUS_RUNG> buses = busRungRepository.findByRungStatus("1");
        if (buses.isEmpty()) return;

        buses.forEach(bus -> {
            // 노선 경로(Vertex) 데이터 가져오기
            List<VertexDTO> vertices = routeService.getRouteVertexes(bus.getRouteId());
            if (vertices.isEmpty()) return;

            // 현재 좌표
            BigDecimal currentX = bus.getXCord();
            BigDecimal currentY = bus.getYCord();

            // 현재 노드(Vertex) 인덱스 찾기
            int currentIndex = findCurrentVertexIndex(vertices, currentX, currentY);
            if (currentIndex < 0 || currentIndex >= vertices.size() - 1) return;

            // 다음 노드(Vertex)로 이동
            VertexDTO nextVertex = vertices.get(currentIndex + 1);
            double distance = calculateGeographicalDistance(currentY, currentX, nextVertex.getYcord(), nextVertex.getXcord());
            double speed = calculateSpeed(distance);

            // 상태 업데이트
            String busLocationDivision = determineBusLocation(bus, nextVertex, distance);

            // 버스 데이터 갱신
            bus.setXCord(nextVertex.getXcord());
            bus.setYCord(nextVertex.getYcord());
            bus.setMomentSpeed(BigDecimal.valueOf(speed));
            bus.setPointPassageDate(LocalDateTime.now());
            bus.setBusLocationDivision(busLocationDivision);
        });

        // 모든 버스 데이터 저장
        busRungRepository.saveAll(buses);
    }

    /**
     * Haversine Formula를 사용한 지리적 거리 계산 (미터 단위)
     *
     * @param lat1 현재 Y좌표 (위도)
     * @param lon1 현재 X좌표 (경도)
     * @param lat2 다음 Y좌표 (위도)
     * @param lon2 다음 X좌표 (경도)
     * @return 거리 (미터)
     */
    private double calculateGeographicalDistance(BigDecimal lat1, BigDecimal lon1, BigDecimal lat2, BigDecimal lon2) {
        final double R = 6371e3; // 지구 반지름 (미터)
        double phi1 = Math.toRadians(lat1.doubleValue());
        double phi2 = Math.toRadians(lat2.doubleValue());
        double deltaPhi = Math.toRadians(lat2.subtract(lat1).doubleValue());
        double deltaLambda = Math.toRadians(lon2.subtract(lon1).doubleValue());

        double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2)
                + Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    /**
     * 이동 속도 계산 (km/h 단위)
     *
     * @param distance 이동 거리 (미터)
     * @return 속도 (km/h)
     */
    private double calculateSpeed(double distance) {
        return (distance / (updateInterval / 1000)) * 3.6; // m/s -> km/h 변환
    }

    /**
     * 현재 좌표와 가장 가까운 Vertex의 인덱스 반환
     *
     * @param vertices   노선 경로(Vertex) 리스트
     * @param currentX   현재 X 좌표
     * @param currentY   현재 Y 좌표
     * @return Vertex 인덱스 (없으면 -1)
     */
    private int findCurrentVertexIndex(List<VertexDTO> vertices, BigDecimal currentX, BigDecimal currentY) {
        for (int i = 0; i < vertices.size(); i++) {
            VertexDTO vertex = vertices.get(i);
            if (isCloseEnough(vertex.getXcord(), currentX) && isCloseEnough(vertex.getYcord(), currentY)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 근사값 비교
     *
     * @param a 첫 번째 값
     * @param b 두 번째 값
     * @return 근사값 여부
     */
    private boolean isCloseEnough(BigDecimal a, BigDecimal b) {
        return a.subtract(b).abs().doubleValue() < 0.0001;
    }

    /**
     * 버스의 상태 결정 (진입/진출/정주기)
     *
     * @param bus        현재 버스 데이터
     * @param nextVertex 다음 Vertex 데이터
     * @param distance   두 좌표 간 거리
     * @return 상태 코드 ("01", "02", "03")
     */
    private String determineBusLocation(C_TC_BUS_RUNG bus, VertexDTO nextVertex, double distance) {
        if (distance < 10) {
            return "02"; // 진입
        } else if (distance > 20) {
            return "03"; // 진출
        }
        return "01"; // 정주기
    }
}
