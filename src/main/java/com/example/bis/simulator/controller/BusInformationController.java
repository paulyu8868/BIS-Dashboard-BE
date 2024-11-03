package com.example.bis.simulator.controller;

import com.example.bis.simulator.dto.BusInformationDTO;
import com.example.bis.simulator.model.M_OP_BUS;
import com.example.bis.simulator.model.M_OP_OBU;
import com.example.bis.simulator.service.BusInformationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bus-info")
public class BusInformationController {

    private final BusInformationService busInformationService;

    @Autowired
    public BusInformationController(BusInformationService busInformationService) {
        this.busInformationService = busInformationService;
    }

    // OBU ID로 BusInformationDTO 조회
    @GetMapping("/{obuId}")
    public ResponseEntity<BusInformationDTO> getBusInformationByObuId(@PathVariable String obuId) {
        return busInformationService.getBusInformationByObuId(obuId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}