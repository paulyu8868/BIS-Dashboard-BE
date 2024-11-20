package com.example.bis.simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class NodeData {
    private String nodeId;
    private BigDecimal xcord;
    private BigDecimal ycord;
}
