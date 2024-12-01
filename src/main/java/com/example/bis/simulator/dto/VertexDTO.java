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
public class VertexDTO {
    private String linkId;
    private Integer pointSqno;  // ROUTE_POINT의 순번
    private Integer sqno;       // VERTEX의 순번
    private BigDecimal xcord;
    private BigDecimal ycord;
}