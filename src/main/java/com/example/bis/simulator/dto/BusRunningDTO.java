package com.example.bis.simulator.dto;

import lombok.Data;

@Data
public class BusRunningDTO {
    private String obuId;
    private String routeId;
    private String rungStatus;  // 운행 상태 ("1": 운행중, "0": 운행종료)
}