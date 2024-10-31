package com.example.bis.simulator.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "M_OP_BUS")
@Data  // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
public class M_OP_BUS {

    @Id
    @Column(name = "BUS_ID", nullable = false)
    private Integer busId;

    @Column(name = "BUS_NO", length = 20)
    private String busNo;

    @Column(name = "RUNG_TYPE", length = 2)
    private String rungType;

    @Column(name = "CMNUSE_DIV", length = 1)
    private String commonUseDivision;

    @Column(name = "SPCL_BUS_DIV", length = 1)
    private String specialBusDivision;

    @Column(name = "BUS_SIZE", length = 10)
    private String busSize;

    @Column(name = "RIDE_FXNO")
    private Integer rideFixNumber;

    @Column(name = "CO_ID", length = 10)
    private String companyId;

    @Column(name = "CMPTNC_INST_NM", length = 30)
    private String competenceInstitutionName;

    @Column(name = "INSP_TGT_DT")
    private LocalDateTime inspectionTargetDate;

    @Column(name = "INSP_DT")
    private LocalDateTime inspectionDate;

    @Column(name = "ENDPROD_DT")
    private LocalDateTime endProductionDate;

    @Column(name = "VHCL_EXPIRE_DT")
    private LocalDateTime vehicleExpireDate;

    @Column(name = "SCPVHC_DT")
    private LocalDateTime scrapVehicleDate;

    @Column(name = "RMRK", length = 300)
    private String remarks;

    @Column(name = "CRT_DT")
    private LocalDateTime creationDate;

    @Column(name = "UPD_DT")
    private LocalDateTime updateDate;

    @Column(name = "GNSS_TRMNL_ID", length = 20)
    private String gnssTerminalId;

    @Column(name = "SEAT_CNT")
    private Integer seatCount;

    @Column(name = "STNDG_FXNO", length = 20)
    private String standingFixNumber;

    @Column(name = "BUS_MODEL", length = 50)
    private String busModel;

    @Column(name = "LFBUS_YN", length = 1)
    private String lowFloorBusYn;

    // 기본 생성자는 Lombok에 의해 자동 생성됨
}
