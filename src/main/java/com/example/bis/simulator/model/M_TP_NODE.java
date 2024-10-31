package com.example.bis.simulator.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "M_TP_NODE")
@Data
@NoArgsConstructor // 기본 생성자 생성
public class M_TP_NODE {

    @Id
    @Column(name = "NODE_ID", length = 10, nullable = false)
    private String nodeId;

    @Column(name = "NODE_TYPE", length = 3)
    private String nodeType;

    @Column(name = "NODE_NM", length = 100)
    private String nodeNm;

    @Column(name = "NODE_SNM", length = 50)
    private String nodeSnm;

    @Column(name = "XCORD", precision = 14, scale = 8)
    private BigDecimal xcord;

    @Column(name = "YCORD", precision = 14, scale = 8)
    private BigDecimal ycord;

    @Column(name = "ADSTDG_CD", length = 10)
    private String adstdgCd;

    @Column(name = "RGSPH_CD", length = 10)
    private String rgsphCd;

    @Column(name = "ENT_JDG_DIST", precision = 7, scale = 1)
    private BigDecimal entJdgDist;

    @Column(name = "EXT_JDG_DIST", precision = 7, scale = 1)
    private BigDecimal extJdgDist;

    @Column(name = "RTTN_LIMIT_YN", length = 1)
    private String rttnLimitYn;

    @Column(name = "RMRK", length = 300)
    private String rmrk;

    @Column(name = "VER_ID")
    private Integer verId;

    @Column(name = "UPD_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updDt;

    @Column(name = "UPDUSR_ID", length = 20)
    private String updusrId;

    @Column(name = "SYNC_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date syncDt;
}
