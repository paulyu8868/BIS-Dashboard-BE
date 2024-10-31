package com.example.bis.simulator.model;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "M_OP_ROUTE")
@Data
public class M_OP_ROUTE {

    @Id
    @Column(name = "ROUTE_ID", nullable = false, length = 10)
    private String routeId;

    @Column(name = "ROUTE_NM", length = 50)
    private String routeName;

    @Column(name = "RUNG_TYPE", length = 3)
    private String rungType;

    @Column(name = "ROUTE_NO", length = 10)
    private String routeNo;

    @Column(name = "ROUTE_TYPE", length = 10)
    private String routeType;

    @Column(name = "STPT_BSTP_ID", length = 10)
    private String startBusStopId;

    @Column(name = "EDPT_BSTP_ID", length = 10)
    private String endBusStopId;

    @Column(name = "LEN", precision = 7, scale = 1)
    private BigDecimal length;

    @Column(name = "RUNG_STRT_DT")
    private LocalDateTime rungStartDate;

    @Column(name = "RUNG_END_DT")
    private LocalDateTime rungEndDate;

    @Column(name = "MIN_ALVHC_INTRVL")
    private Integer minInterval;

    @Column(name = "MAX_ALVHC_INTRVL")
    private Integer maxInterval;

    @Column(name = "RQRD_TIME")
    private Integer requiredTime;

    @Column(name = "ROUTE_KOREAN_NM", length = 100)
    private String routeKoreanName;

    @Column(name = "ROUTE_MAX_SPD", precision = 4, scale = 1)
    private BigDecimal routeMaxSpeed;

    @Column(name = "PERMIT_BUS_CNT")
    private Integer permitBusCount;

    @Column(name = "CMPTNC_INST_NM", length = 50)
    private String competentInstitutionName;

    @Column(name = "ROUTE_COLOR_CD", length = 3)
    private String routeColorCode;

    @Column(name = "ROUTE_ALVHC_EXCT_YN", length = 1)
    private String routeAlvhcExemptYn;

    @Column(name = "WAREA_ROUTE_YN", length = 1)
    private String wareaRouteYn;

    @Column(name = "DIR_NM", length = 100)
    private String directionName;

    @Column(name = "EDPT_DIR_NM", length = 100)
    private String endPointDirectionName;

    @Column(name = "TNPT_DIR_NM", length = 100)
    private String tnptDirectionName;

    @Column(name = "TNPT_POINT_SQNO")
    private Integer tnptPointSqno;

    @Column(name = "MROUTE_ID")
    private Integer mrouteId;

    @Column(name = "BRLINE_VAPT", length = 300)
    private String brlineVapt;

    @Column(name = "MPPNG_ROUTE_NO", length = 10)
    private String mppngRouteNo;

    @Column(name = "APP_ROUTE_DESCR", length = 300)
    private String appRouteDescription;

    @Column(name = "APP_TNPT_DESCR", length = 300)
    private String appTnptDescription;

    @Column(name = "APP_BRLINE_SQNO")
    private Integer appBrlineSqno;

    @Column(name = "APP_ALVHC_DSPL_YN", length = 1)
    private String appAlvhcDisplayYn;

    @Column(name = "SUPT_NO", length = 2)
    private String supportNo;

    @Column(name = "SPTMNY_PRVSN_YN", length = 1)
    private String sptmnyProvisionYn;

    @Column(name = "RMRK", length = 300)
    private String remark;

    @Column(name = "VER_ID")
    private Integer versionId;

    @Column(name = "UPD_DT")
    private LocalDateTime updateDate;

    @Column(name = "UPDUSR_ID", length = 20)
    private String updateUserId;

    @Column(name = "ROUTE_DESCR", length = 255)
    private String routeDescription;

    @Column(name = "RMTART_YN", length = 1)
    private String remoteArtYn;

    @Column(name = "MYBI_ROUTE_ID", length = 20)
    private String mybiRouteId;
}
