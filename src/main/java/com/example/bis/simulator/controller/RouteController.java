package com.example.bis.simulator.controller;

import com.example.bis.simulator.dto.BusStopDTO;
import com.example.bis.simulator.dto.NodeDTO;
import com.example.bis.simulator.dto.VertexDTO;
import com.example.bis.simulator.repository.RouteRepository;
import com.example.bis.simulator.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/simulator")
public class RouteController { // 노선의 정류장,노드,버텍스 등 조회

    private static final Logger logger = LoggerFactory.getLogger(RouteController.class);
    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    // 정류장 조회 엔드포인트
    @GetMapping("/route/{routeId}/stops")
    public ResponseEntity<List<BusStopDTO>> getBusStops(@PathVariable String routeId) {
        try {
            logger.info("정류장 정보 조회 요청 - ROUTE_ID: {}", routeId);
            List<BusStopDTO> busStops = routeService.getBusStops(routeId);

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

    // 노선의 노드 조회 엔드포인트
    @GetMapping("/route/{routeId}/nodes")
    public ResponseEntity<List<NodeDTO>> getRouteNodes(@PathVariable String routeId) {
        try {
            logger.info("노선 노드 정보 조회 요청 - routeId: {}", routeId);
            List<NodeDTO> nodes = routeService.getRouteNodes(routeId);

            if (nodes.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(nodes);
        } catch (Exception e) {
            logger.error("노선 노드 정보 조회 실패", e);
            return ResponseEntity.internalServerError().build();
        }
    }


    // 노선의 모든 버텍스 위치 조회 (순서 정렬 완료)
    @GetMapping("/route/{routeId}/vertexes")
    public ResponseEntity<List<VertexDTO>> getRouteVertexes(@PathVariable String routeId) {
        try {
            logger.info("노선 버텍스 조회 요청 - ROUTE_ID: {}", routeId);
            List<VertexDTO> vertexes = routeService.getRouteVertexes(routeId);

            if (vertexes.isEmpty()) {
                logger.warn("버텍스 정보가 없습니다 - ROUTE_ID: {}", routeId);
                return ResponseEntity.notFound().build();
            }

            logger.info("버텍스 조회 성공 - ROUTE_ID: {}, 버텍스 수: {}", routeId, vertexes.size());
            return ResponseEntity.ok(vertexes);

        } catch (Exception e) {
            logger.error("버텍스 조회 실패 - ROUTE_ID: " + routeId, e);
            return ResponseEntity.internalServerError().build();
        }
    }


}