package com.example.bis.simulator.controller;

import com.example.bis.simulator.dto.BusLocationDTO;
import com.example.bis.simulator.dto.BusStopDTO;
import com.example.bis.simulator.dto.VertexDTO;
import com.example.bis.simulator.service.BusRungService;
import com.example.bis.simulator.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/route")
@CrossOrigin(origins = "http://localhost:3000") // CORS 허용
public class RouteController {

    private final RouteService routeService;
    private final BusRungService busRungService;

    public RouteController(RouteService routeService, BusRungService busRungService) {
        this.routeService = routeService;
        this.busRungService = busRungService;
    }

    /**
     * 특정 노선의 정류장 데이터를 조회합니다.
     *
     * @param routeId 노선 ID
     * @return BusStopDTO 리스트
     */
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

    /**
     * 특정 노선의 모든 버텍스 데이터를 조회합니다.
     *
     * @param routeId 노선 ID
     * @return VertexDTO 리스트
     */
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

    /**
     * 특정 노선에 운행 중인 버스의 위치 데이터를 조회합니다.
     *
     * @param routeId 노선 ID
     * @return BusLocationDTO 리스트
     */
    @GetMapping("/{routeId}/buses/stops")
    public ResponseEntity<List<BusLocationDTO>> getBusRunsByRouteId(@PathVariable String routeId) {
        try {
            List<BusLocationDTO> busLocations = busRungService.getObuAndPassagePointIds(routeId);

            if (busLocations.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(busLocations);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
