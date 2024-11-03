package com.example.bis.simulator.service;

import com.example.bis.simulator.dto.VertexDTO;
import com.example.bis.simulator.model.C_TC_BUS_RUNG;
import com.example.bis.simulator.repository.BusRunningRepository;
import com.example.bis.simulator.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;


@Service
public class BusRunningService {

    private final BusRunningRepository busRunningRepository;
    private final RouteRepository routeRepository;
    private static final Logger logger = LoggerFactory.getLogger(BusRunningService.class);


    @Autowired
    public BusRunningService(BusRunningRepository busRunningRepository, RouteRepository routeRepository) {
        this.busRunningRepository = busRunningRepository;
        this.routeRepository = routeRepository;
    }


    @Transactional
    public boolean startBusOperation(String obuId, String routeId) {
        try {
            // 노선의 첫 번째 버텍스 조회
            VertexDTO firstVertex = routeRepository.findVertexesByRouteId(routeId)
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("노선의 버텍스 정보가 없습니다."));

            // 버스 운행 정보 설정
            C_TC_BUS_RUNG busRunning = new C_TC_BUS_RUNG();
            busRunning.setObuId(obuId);
            busRunning.setRouteId(routeId);
            busRunning.setXCord(firstVertex.getXcord()); // X좌표
            busRunning.setYCord(firstVertex.getYcord()); // Y좌표
            busRunning.setRungStatus("1");  // 운행 상태를 1로 설정
            busRunning.setUpdateDate(LocalDateTime.now()); // 시간 업데이트

            // 저장
            busRunningRepository.save(busRunning);
            logger.info("버스 운행 시작 - OBU_ID: {}, ROUTE_ID: {}, 시작 위치: ({}, {})",
                    obuId, routeId, firstVertex.getXcord(), firstVertex.getYcord());

            return true;
        } catch (Exception e) {
            logger.error("버스 운행 시작 실패 - OBU_ID: " + obuId, e);
            throw new RuntimeException("버스 운행 시작 처리 실패", e);
        }
    }

    // 운행 종료
    @Transactional
    public boolean stopAllBusesOnRoute(String routeId) {
        try {
            int updated = busRunningRepository.stopAllBusesOnRoute(routeId);
            return updated > 0;
        } catch (Exception e) {
            throw new RuntimeException("노선 운행 종료 처리 실패", e);
        }
    }
}


/*
@Service
public class BusRunningService {
    private final BusRunningRepository busRunningRepository;
    @Autowired
    public BusRunningService(BusRunningRepository busRunningRepository) {
        this.busRunningRepository = busRunningRepository;
    }
    // 운행 시작
    @Transactional
    public boolean startBusOperation(String obuId) {
        try {
            int updated = busRunningRepository.startBusOperation(obuId);
            return updated > 0;
        } catch (Exception e) {
            throw new RuntimeException("운행 시작 처리 실패", e);
        }
    }
    // 운행 종료
    @Transactional
    public boolean stopAllBusesOnRoute(String routeId) {
        try {
            int updated = busRunningRepository.stopAllBusesOnRoute(routeId);
            return updated > 0;
        } catch (Exception e) {
            throw new RuntimeException("노선 운행 종료 처리 실패", e);
        }
    }
}
 */