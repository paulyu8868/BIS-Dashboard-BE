package com.example.bis.simulator.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class NodeDTO {
    private String nodeId;
    private String nodeType;
    private BigDecimal xcord;
    private BigDecimal ycord;
    private Integer pointSqno;  // 노선에서의 순번
}