package com.example.bis.simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusStopDTO {
    private String bstpId;
    private String bstpNm;
    private BigDecimal xcord;
    private BigDecimal ycord;
    private Integer pointSqno;
}
