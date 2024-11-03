package com.example.bis.simulator.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BusInformationDTO {
    private String busNo;
    private Integer rideFixNumber;
    private LocalDateTime inspectionDate;
    private String mnfctCoNm; // OBU
}
