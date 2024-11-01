package com.example.bis.simulator.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;


@Data
@Builder
public class BusLocationDTO {
    private String obuId;
    private String routeId;
    private BigDecimal xcord;  // xCord -> xcord
    private BigDecimal ycord;  // yCord -> ycord
    private String rungStatus;
}