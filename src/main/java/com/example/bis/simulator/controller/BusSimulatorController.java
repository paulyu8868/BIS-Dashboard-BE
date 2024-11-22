package com.example.bis.simulator.controller;

import com.example.bis.simulator.dto.BusDataResponse;
import com.example.bis.simulator.service.BusSimulatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 버스 시뮬레이터 컨트롤러.
 */
@RestController
@RequestMapping("/api/simulator")
@CrossOrigin(origins = "http://localhost:3000") // CORS 허용
@RequiredArgsConstructor
public class BusSimulatorController {

    private final BusSimulatorService busSimulatorService;

    /**
     * 상위 10개의 버스 데이터를 반환합니다.
     *
     * @return BusDataResponse 리스트
     */
    @GetMapping("/buses/top10")
    public ResponseEntity<List<BusDataResponse>> getTopBuses() {
        return ResponseEntity.ok(busSimulatorService.getTopBuses());
    }

    /**
     * 특정 OBU ID와 노선 ID로 시뮬레이터를 시작합니다.
     *
     * @param request busId와 routeId를 포함하는 요청
     * @return 성공 메시지 또는 오류 메시지
     */
    @PostMapping("/start")
    public ResponseEntity<String> startSimulation(@RequestBody Map<String, String> request) {
        String busId = request.get("busId");
        String routeId = request.get("routeId");

        if (busId == null || routeId == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("버스 ID와 노선 ID는 필수입니다.");
        }

        try {
            busSimulatorService.startSimulation(busId, routeId);
            return ResponseEntity.ok("시뮬레이터가 성공적으로 시작되었습니다.");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("시뮬레이터 시작 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * 모든 버스를 초기화합니다.
     *
     * @param obuIds 초기화할 OBU ID 리스트
     */
    @PostMapping("/initialize/all")
    public void initializeAllBuses(@RequestBody List<String> obuIds) {
        busSimulatorService.initializeAllBuses(obuIds);
    }
}
