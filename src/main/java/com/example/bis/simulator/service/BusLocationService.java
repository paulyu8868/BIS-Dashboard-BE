package com.example.bis.simulator.service;

import com.example.bis.simulator.dto.BusLocationDTO;
import com.example.bis.simulator.model.C_TC_BUS_RUNG;
import com.example.bis.simulator.repository.BusRunningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusLocationService {

    private final BusRunningRepository busRunningRepository;

    @Autowired
    public BusLocationService(BusRunningRepository busRunningRepository) {
        this.busRunningRepository = busRunningRepository;
    }

    @Transactional(readOnly = true)
    public List<BusLocationDTO> getAllBusLocations(String routeId) {
        List<C_TC_BUS_RUNG> runningBuses = busRunningRepository.findAllRunningBusesByRouteId(routeId);

        return runningBuses.stream()
                .map(bus -> BusLocationDTO.builder()
                        .obuId(bus.getObuId())
                        .routeId(bus.getRouteId())
                        .xcord(bus.getXCord())
                        .ycord(bus.getYCord())
                        .rungStatus(bus.getRungStatus())
                        .passagePointSqno(bus.getPassagePointSqNo())
                        .lastUpdateTime(bus.getUpdateDate())
                        .build())
                .collect(Collectors.toList());
    }
}
