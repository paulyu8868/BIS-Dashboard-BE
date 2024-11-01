package com.example.bis.simulator.controller;

import com.example.bis.simulator.dto.BusStopDTO;
import com.example.bis.simulator.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/simulator")
public class RouteController {

    private static final Logger logger = LoggerFactory.getLogger(RouteController.class);
    private final RouteRepository routeRepository;

    @Autowired
    public RouteController(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @GetMapping("/route/{routeId}/stops")
    public ResponseEntity<List<BusStopDTO>> getBusStops(@PathVariable String routeId) {
        try {
            logger.info("정류장 정보 조회 요청 - ROUTE_ID: {}", routeId);
            List<BusStopDTO> busStops = routeRepository.findBusStopsByRouteId(routeId);

            if (busStops.isEmpty()) {
                logger.warn("정류장 정보가 없습니다 - ROUTE_ID: {}", routeId);
                return ResponseEntity.notFound().build();
            }

            logger.info("정류장 정보 조회 성공 - ROUTE_ID: {}, 정류장 수: {}", routeId, busStops.size());
            return ResponseEntity.ok(busStops);

        } catch (Exception e) {
            logger.error("정류장 정보 조회 실패 - ROUTE_ID: " + routeId, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}