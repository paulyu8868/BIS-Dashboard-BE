package com.example.bis.simulator.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "M_TP_LINK")
@Data
@NoArgsConstructor // 기본 생성자 생성
public class M_TP_LINK {

    @Id
    @Column(name = "LINK_ID", length = 10, nullable = false)
    private String linkId;

    @Column(name = "LANE_CNT")
    private Integer laneCnt;

    @Column(name = "LINK_NM", length = 30)
    private String linkNm;

    @Column(name = "ROAD_GRD", length = 3)
    private String roadGrd;

    @Column(name = "ROAD_TYPE", length = 3)
    private String roadType;

    @Column(name = "ROAD_NO", length = 10)
    private String roadNo;

    @Column(name = "LEN", precision = 7, scale = 1)
    private BigDecimal len;

    @Column(name = "STRT_NODE_ID", length = 10)
    private String strtNodeId;

    @Column(name = "END_NODE_ID", length = 10)
    private String endNodeId;

    @Column(name = "RGSPH_CD", length = 10)
    private String rgsphCd;

    @Column(name = "USE_YN", length = 1)
    private String useYn;

    @Column(name = "MLTSCTN_YN", length = 1)
    private String mltsctnYn;

    @Column(name = "CNTRD_CD", length = 4)
    private String cntrdCd;

    @Column(name = "HGHST_LIMIT_SPD", precision = 4, scale = 1)
    private BigDecimal hghstLimitSpd;

    @Column(name = "PASS_LIMIT_VHCL", length = 3)
    private String passLimitVhcl;

    @Column(name = "PASS_LIMIT_WEIGHT")
    private Integer passLimitWeight;

    @Column(name = "PASS_LIMIT_HEIGHT")
    private Integer passLimitHeight;

    @Column(name = "RMRK", length = 300)
    private String rmrk;

    @Column(name = "VER_ID")
    private Integer verId;

    @Column(name = "UPD_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updDt;

    @Column(name = "UPDUSR_ID", length = 10)
    private String updusrId;

    @Column(name = "SYNC_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date syncDt;
}
