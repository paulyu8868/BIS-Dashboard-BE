package com.example.bis.simulator.controller;

import com.example.bis.simulator.dto.BusDataDTO;
import com.example.bis.simulator.service.BusSimulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/busdata")
public class BusDataController {

    @Autowired
    private BusSimulatorService busSimulatorService;

    @PostMapping
    public ResponseEntity<BusDataDTO> sendBusData(@RequestBody BusDataDTO busDataDTO) {
        // BusSimulatorService의 메서드 호출하여 시뮬레이션 처리
        busSimulatorService.simulateBusMovement(busDataDTO.getBusId(), busDataDTO.getPresttId());

        // 처리된 DTO 반환
        return ResponseEntity.ok(busDataDTO); // 성공적으로 처리된 DTO 반환
    }
}
