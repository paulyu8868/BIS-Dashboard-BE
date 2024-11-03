package com.example.bis.simulator.controller;

import com.example.bis.simulator.service.BusRunningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulator/operation")
public class BusRunningController { // 운행시작, 운행종료 버튼 관리

    private static final Logger logger = LoggerFactory.getLogger(BusRunningController.class);
    private final BusRunningService busRunningService;

    @Autowired
    public BusRunningController(BusRunningService busRunningService) {
        this.busRunningService = busRunningService;
    }

    // 해당 버스 운행 상태
    // 운행시작 버튼
    @PostMapping("/start")
    public ResponseEntity<Void> startOperation(@RequestParam String obuId,
                                               @RequestParam String routeId) {
        try {
            logger.info("운행 시작 요청 - OBU_ID: {}, ROUTE_ID: {}", obuId, routeId);
            boolean success = busRunningService.startBusOperation(obuId, routeId);

            if (success) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("운행 시작 처리 실패", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // 운행종료 버튼
    @PostMapping("/stop-route")
    public ResponseEntity<Void> stopRouteOperation(@RequestParam String routeId) {
        try {
            logger.info("노선 운행 종료 요청 - ROUTE_ID: {}", routeId);
            boolean success = busRunningService.stopAllBusesOnRoute(routeId); // 모든 버스 운행상태 0으로 변경
            if (success) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("노선 운행 종료 처리 실패 - ROUTE_ID: " + routeId, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}