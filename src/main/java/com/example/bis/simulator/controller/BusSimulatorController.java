package com.example.bis.simulator.controller;

import com.example.bis.simulator.dto.BusDataResponse;
import com.example.bis.simulator.dto.BusSimulationResponse;
import com.example.bis.simulator.dto.BusStopDTO;
import com.example.bis.simulator.dto.VertexDTO;
import com.example.bis.simulator.service.BusSimulatorService;
import com.example.bis.simulator.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/simulator")
@CrossOrigin(origins = "http://localhost:3000") // CORS 허용
@RequiredArgsConstructor
public class BusSimulatorController {

    private final BusSimulatorService busSimulatorService;
    private final RouteService routeService;

    @GetMapping("/buses/{routeId}")
    public ResponseEntity<List<BusDataResponse>> getBusesByRouteId(@PathVariable String routeId) {
        try {
            List<BusDataResponse> buses = busSimulatorService.getBusesByRouteId(routeId);
            if (buses.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(buses);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/start")
    public ResponseEntity<?> startSimulation(@RequestBody Map<String, String> request) {
        String obuId = request.get("obuId");
        String routeId = request.get("routeId");

        if (obuId == null || routeId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OBU ID와 노선 ID는 필수입니다.");
        }

        try {
            List<BusStopDTO> busStops = routeService.getBusStops(routeId);
            List<VertexDTO> vertices = routeService.getRouteVertexes(routeId);

            if (busStops.isEmpty() || vertices.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("노선 경로 또는 정류장 정보가 없습니다.");
            }

            System.out.println("vertices.get(0): = " + vertices.get(0));
            busSimulatorService.startSimulation(obuId, vertices.get(0)); // 초기 위치로 초기화

            return ResponseEntity.ok(Map.of(
                    "message", "시뮬레이터가 성공적으로 시작되었습니다.",
                    "busStops", busStops,
                    "vertices", vertices
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("시뮬레이터 시작 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    @GetMapping("/locations/{routeId}")
    public ResponseEntity<List<BusSimulationResponse>> getBusLocationsByRouteId(@PathVariable String routeId) {
        try {
            List<BusSimulationResponse> locations = busSimulatorService.getBusLocationsByRouteId(routeId);
            if (locations.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(locations);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
