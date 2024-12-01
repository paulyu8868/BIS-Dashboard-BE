package com.example.bis.simulator.controller;

import com.example.bis.simulator.dto.BusStopDTO;
import com.example.bis.simulator.dto.RouteData;
import com.example.bis.simulator.dto.VertexDTO;
import com.example.bis.simulator.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/route")
@CrossOrigin(origins = "http://localhost:3000") // CORS 허용
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/top10") //"최적화된 상위 10개 경로 조회", description = "최적화된 경로 데이터를 상위 10개 반환합니다.")
    public List<RouteData> getTop10Routes() {
        return routeService.getOptimizedRouteData();
    }


    // 정류장 조회 엔드포인트
    @GetMapping("/busstops/{routeId}")
    public ResponseEntity<List<BusStopDTO>> getBusStops(@PathVariable String routeId) {
        try {
            List<BusStopDTO> busStops = routeService.getBusStops(routeId);

            if (busStops.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(busStops);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    // 노선의 모든 버텍스 위치 조회 (순서 정렬 완료)
    @GetMapping("/vertices/{routeId}")
    public ResponseEntity<List<VertexDTO>> getRouteVertexes(@PathVariable String routeId) {
        try {
            List<VertexDTO> vertexes = routeService.getRouteVertexes(routeId);

            if (vertexes.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(vertexes);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
