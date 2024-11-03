package com.example.bis.simulator.model;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "M_OP_ROUTE_POINT")
@Data
@IdClass(RoutePointId.class)
public class M_OP_ROUTE_POINT {

    @Id
    @Column(name = "ROUTE_ID", nullable = false, length = 10)
    private String routeId;

    @Id
    @Column(name = "POINT_SQNO", nullable = false)
    private Integer pointSqno;

    @Column(name = "POINT_DIV", length = 10)
    private String pointDiv;

    @Column(name = "POINT_ID", length = 10)
    private String pointId;

    @Column(name = "BEF_POINT_ID", length = 10)
    private String beforePointId;

    @Column(name = "LEN", precision = 7, scale = 1)
    private BigDecimal length;

    @Column(name = "EDPT_RSDL_DIST", precision = 7, scale = 1)
    private BigDecimal endPointResidualDistance;

    @Column(name = "BSTP_SQNO")
    private Integer busStopSqno;

    @Column(name = "MAIN_VAPT_YN", length = 1)
    private String mainVaptYn;

    @Column(name = "STPT_EDPT_DIV", length = 1)
    private String startPointEndPointDiv;

    @Column(name = "DETECT_YN", length = 1)
    private String detectYn;

    @Column(name = "DETECT_SQNO")
    private Integer detectSqno;

    @Column(name = "BSIDE_SRVC_YN", length = 1)
    private String besideServiceYn;

    @Column(name = "VER_ID")
    private Integer versionId;

    @Column(name = "UPD_DT")
    private LocalDateTime updateDate;

    @Column(name = "UPDUSR_ID", length = 10)
    private String updateUserId;
}