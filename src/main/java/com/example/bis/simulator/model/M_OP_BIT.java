package com.example.bis.simulator.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "M_OP_BIT")
@Data
public class M_OP_BIT {

    @Id
    @Column(name = "BIT_ID", length = 10, nullable = false)
    private String bitId;

    @Column(name = "BIT_NM", length = 100)
    private String bitName;

    @Column(name = "BIT_TYPE", length = 3)
    private String bitType;

    @Column(name = "IP", length = 20)
    private String ip;

    @Column(name = "USE_YN", length = 1)
    private String useYn;

    @Column(name = "VPN_YN", length = 1)
    private String vpnYn;

    @Column(name = "INSTL_DT")
    private LocalDateTime installationDate;

    @Column(name = "BSTP_ID", length = 10)
    private String busStopId;

    @Column(name = "INSTL_LOC", length = 50)
    private String installationLocation;

    @Column(name = "INSTL_CO_NM", length = 50)
    private String installationCompanyName;

    @Column(name = "COMM_BIZENT", length = 50)
    private String communicationBusinessEntity;

    @Column(name = "MEMO", length = 255)
    private String memo;

    @Column(name = "CNNCT_TYPE", length = 3)
    private String connectionType;

    @Column(name = "CRT_DT")
    private LocalDateTime creationDate;

    @Column(name = "UPD_DT")
    private LocalDateTime updateDate;

    @Column(name = "UPDUSR_ID", length = 10)
    private String updateUserId;

    @Column(name = "XCORD", precision = 14, scale = 8)
    private BigDecimal xCoordinate;

    @Column(name = "YCORD", precision = 14, scale = 8)
    private BigDecimal yCoordinate;

    @Column(name = "DPRTR_PLNND_PRVSN_YN", length = 1)
    private String departurePlannedProvisionYn;

    // 기본 생성자는 Lombok에 의해 자동 생성됨
}
