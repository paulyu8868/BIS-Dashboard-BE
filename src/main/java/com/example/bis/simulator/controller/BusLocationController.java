package com.example.bis.simulator.controller;

import com.example.bis.simulator.dto.BusLocationDTO;
import com.example.bis.simulator.service.BusLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/simulator")
public class BusLocationController {

    private static final Logger logger = LoggerFactory.getLogger(BusLocationController.class);
    private final BusLocationService busLocationService;

    @Autowired
    public BusLocationController(BusLocationService busLocationService) {
        this.busLocationService = busLocationService;
    }

    @GetMapping("/bus-location")
    public ResponseEntity<BusLocationDTO> getBusLocation(
            @RequestParam String obuId,
            @RequestParam String routeId) {
        try {
            logger.info("버스 위치 조회 요청 - OBU_ID: {}, ROUTE_ID: {}", obuId, routeId);
            BusLocationDTO location = busLocationService.getBusLocation(obuId, routeId);
            return ResponseEntity.ok(location);
        } catch (Exception e) {
            logger.error("버스 위치 조회 실패", e);
            return ResponseEntity.notFound().build();
        }
    }
}