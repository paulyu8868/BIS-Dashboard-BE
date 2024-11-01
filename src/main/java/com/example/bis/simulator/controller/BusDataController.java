package com.example.bis.simulator.controller;

import com.example.bis.simulator.dto.BusDataDTO;
import com.example.bis.simulator.service.BusSimulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/busdata")
public class BusDataController {

    @Autowired
    private BusSimulatorService busSimulatorService;

    // 최근에 처리된 데이터를 저장할 필드
    private BusDataDTO latestBusData;

    @PostMapping
    public ResponseEntity<BusDataDTO> sendBusData(@RequestBody BusDataDTO busDataDTO) {
        // BusSimulatorService의 메서드 호출하여 시뮬레이션 처리
        BusDataDTO processedBusData = busSimulatorService.simulateBusMovement(busDataDTO.getBusId(), busDataDTO.getPresttId());

        // 최신 데이터를 저장
        latestBusData = processedBusData;

        // 처리된 DTO 반환
        return ResponseEntity.ok(latestBusData);
    }

    @GetMapping
    public ResponseEntity<BusDataDTO> getLatestBusData() {
        // 가장 최근에 처리된 데이터 반환
        return ResponseEntity.ok(latestBusData != null ? latestBusData : new BusDataDTO()); // null인 경우 빈 DTO 반환
    }
}
