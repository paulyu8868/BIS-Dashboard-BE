package com.example.bis.simulator.service;

import com.example.bis.simulator.dto.BusLocationDTO;
import com.example.bis.simulator.model.C_TC_BUS_RUNG;
import com.example.bis.simulator.repository.BusRunningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BusLocationService {

    private final BusRunningRepository busRunningRepository;

    @Autowired
    public BusLocationService(BusRunningRepository busRunningRepository) {
        this.busRunningRepository = busRunningRepository;
    }

    @Transactional(readOnly = true)
    public BusLocationDTO getBusLocation(String obuId, String routeId) {
        C_TC_BUS_RUNG busRunning = busRunningRepository
                .findByObuIdAndRouteId(obuId, routeId)
                .orElseThrow(() -> new RuntimeException("운행 중인 버스를 찾을 수 없습니다."));

        return BusLocationDTO.builder()
                .obuId(busRunning.getObuId())
                .routeId(busRunning.getRouteId())
                .xcord(busRunning.getXCord())
                .ycord(busRunning.getYCord())
                .rungStatus(busRunning.getRungStatus())
                .build();
    }
}