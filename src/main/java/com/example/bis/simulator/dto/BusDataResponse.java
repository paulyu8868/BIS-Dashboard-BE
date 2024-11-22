package com.example.bis.simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 버스 정보 응답 DTO.
 */
@Data
@AllArgsConstructor
public class BusDataResponse {
    private Integer busId;  // 버스 ID
    private String busNo;   // 버스 번호
    private String obuId;   // OBU ID
}
