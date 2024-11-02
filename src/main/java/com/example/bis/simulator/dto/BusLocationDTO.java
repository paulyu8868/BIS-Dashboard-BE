package com.example.bis.simulator.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Builder
public class BusLocationDTO {
    private String obuId;
    private String routeId;
    private BigDecimal xcord;
    private BigDecimal ycord;
    private String rungStatus;
    private Integer passagePointSqno;    // 현재 통과 지점 순번
    private LocalDateTime lastUpdateTime; // 마지막 업데이트 시간
}