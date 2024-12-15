package com.example.bis.simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 버스 시뮬레이터 상태 응답 DTO.
 */
@Data
@AllArgsConstructor
public class BusSimulationResponse {
    private String obuId;               // OBU ID
    private String routeId;
    private BigDecimal xCord;           // X 좌표
    private BigDecimal yCord;           // Y 좌표
    private BigDecimal momentSpeed;     // 순간 속도
    private String passagePointId;      // 통과 지점 ID
    private Integer passagePointSqNo;   // 통과 지점 순번
    private LocalDateTime pointPassageDate; // 통과 시각
    private String arrivalPlannedPointId;   // 도착 예정 지점 ID
    private Integer arrivalPlannedPointSqNo; // 도착 예정 지점 순번
    private String busLocationDivision;     // 버스 상태 (진입/진출/정주기)
}
