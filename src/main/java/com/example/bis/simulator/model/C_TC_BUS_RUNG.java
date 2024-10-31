package com.example.bis.simulator.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "C_TC_BUS_RUNG")
@Data
public class C_TC_BUS_RUNG {

    @Id
    @Column(name = "OBU_ID", length = 10)
    private String obuId;

    @Column(name = "ROUTE_ID", length = 10)
    private String routeId;

    @Column(name = "XCORD", precision = 14, scale = 8)
    private BigDecimal xCord;

    @Column(name = "YCORD", precision = 14, scale = 8)
    private BigDecimal yCord;

    @Column(name = "PRGRSS_ANGLE", precision = 5, scale = 2)
    private BigDecimal progressAngle;

    @Column(name = "MMNT_SPD", precision = 4, scale = 1)
    private BigDecimal momentSpeed;

    @Column(name = "PASG_POINT_ID", length = 10)
    private String passagePointId;

    @Column(name = "PASG_POINT_SQNO")
    private Integer passagePointSqNo;

    @Column(name = "POINT_PASG_DT")
    private LocalDateTime pointPassageDate;

    @Column(name = "ARRVL_PLNND_POINT_ID", length = 10)
    private String arrivalPlannedPointId;

    @Column(name = "ARRVL_PLNND_POINT_SQNO")
    private Integer arrivalPlannedPointSqNo;

    @Column(name = "PASG_BSTP_ID", length = 10)
    private String passageBusStopId;

    @Column(name = "BSTP_PASG_DT")
    private LocalDateTime busStopPassageDate;

    @Column(name = "RUNG_DIV", length = 2)
    private String rungDivision;

    @Column(name = "RUNG_STTS", length = 1)
    private String rungStatus;

    @Column(name = "RUNG_STRT_DT")
    private LocalDateTime rungStartDate;

    @Column(name = "RUNG_END_DT")
    private LocalDateTime rungEndDate;

    @Column(name = "RSDL_DIST", precision = 7, scale = 1)
    private BigDecimal residualDistance;

    @Column(name = "ACML_RUNG_DIST")
    private Integer accumulatedRungDistance;

    @Column(name = "BUS_LOC_DIV", length = 10)
    private String busLocationDivision;

    @Column(name = "FRTVHC_OBU_ID", length = 10)
    private String frontVehicleObuId;

    @Column(name = "BCKVHC_OBU_ID", length = 10)
    private String backVehicleObuId;

    @Column(name = "LED_STTS", length = 1)
    private String ledStatus;

    @Column(name = "GPS_STTS", length = 1)
    private String gpsStatus;

    @Column(name = "COMM_SENSTY", length = 10)
    private String communicationSensitivity;

    @Column(name = "ROUTE_BRKAWY_YN", length = 1)
    private String routeBreakaway;

    @Column(name = "SPDNG_YN", length = 1)
    private String speeding;

    @Column(name = "UPD_DT")
    private LocalDateTime updateDate;

    @Column(name = "PASG_BSTP_SQNO")
    private Integer passageBusStopSqNo;

    // 기본 생성자는 Lombok에 의해 자동 생성됨
}
