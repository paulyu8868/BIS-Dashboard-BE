package com.example.bis.simulator.controller;

import com.example.bis.simulator.service.BusRunningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulator/operation")
public class BusRunningController {

    private static final Logger logger = LoggerFactory.getLogger(BusRunningController.class);
    private final BusRunningService busRunningService;

    @Autowired
    public BusRunningController(BusRunningService busRunningService) {
        this.busRunningService = busRunningService;
    }

    @PostMapping("/start")
    public ResponseEntity<Void> startOperation(@RequestParam String obuId) {
        try {
            logger.info("운행 시작 요청 - OBU_ID: {}", obuId);
            boolean success = busRunningService.startBusOperation(obuId);
            if (success) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("운행 시작 처리 실패 - OBU_ID: " + obuId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/stop-route")
    public ResponseEntity<Void> stopRouteOperation(@RequestParam String routeId) {
        try {
            logger.info("노선 운행 종료 요청 - ROUTE_ID: {}", routeId);
            boolean success = busRunningService.stopAllBusesOnRoute(routeId);
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