package com.example.bis.simulator.dto;

import lombok.Data;

@Data
public class BusDataDTO {
    private String colDate;
    private String busId;
    private String presttId;
    private String nextsttId;
    private Double speed;
    private Double azimuth;
    private Double longitude;
    private Double latitude;
    private String eventCode;
}

