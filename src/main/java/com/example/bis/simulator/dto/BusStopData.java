package com.example.bis.simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BusStopData {
    private String busStopId;
    private String busStopName;
    private BigDecimal xcord;
    private BigDecimal ycord;
}
