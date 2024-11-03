package com.example.bis.simulator.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class VertexDTO {
    private String linkId;
    private Integer pointSqno;  // ROUTE_POINT의 순번
    private Integer sqno;       // VERTEX의 순번
    private BigDecimal xcord;
    private BigDecimal ycord;
}