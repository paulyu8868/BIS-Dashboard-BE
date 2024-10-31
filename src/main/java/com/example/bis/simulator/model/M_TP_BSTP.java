package com.example.bis.simulator.model;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "M_TP_BSTP")
@Data
public class M_TP_BSTP {

    @Id
    @Column(name = "BSTP_ID", length = 10, nullable = false)
    private String bstpId;

    @Column(name = "BSTP_NM", length = 60)
    private String bstpNm;

    @Column(name = "BSTP_TYPE", length = 1)
    private String bstpType;

    @Column(name = "CNTR_LANE_YN", length = 1)
    private String cntrLaneYn;

    @Column(name = "USE_YN", length = 1)
    private String useYn;

    @Column(name = "BSTP_ENG_NM", length = 250)
    private String bstpEngNm;

    @Column(name = "BSTP_SNM", length = 60)
    private String bstpSnm;

    @Column(name = "DIR_NM", length = 100)
    private String dirNm;

    @Column(name = "DIR_NM_PSSV_YN", length = 1)
    private String dirNmPssvYn;

    @Column(name = "AZMTH", precision = 10, scale = 2)
    private BigDecimal azmth;

    @Column(name = "XCORD", precision = 14, scale = 8)
    private BigDecimal xcord;

    @Column(name = "YCORD", precision = 14, scale = 8)
    private BigDecimal ycord;

    @Column(name = "ADSTDG_CD", length = 10)
    private String adstdgCd;

    @Column(name = "RGSPH_CD", length = 10)
    private String rgsphCd;

    @Column(name = "CMPTNC_INST_NM", length = 30)
    private String cmptncInstNm;

    @Column(name = "BSTP_SRVC_ID", length = 10)
    private String bstpSrvcId;

    @Column(name = "TRCARD_BSTP_ID", length = 20)
    private String trcardBstpId;

    @Column(name = "OLD_BSTP_ID", length = 10)
    private String oldBstpId;

    @Column(name = "BSTP_RVS_NO")
    private Integer bstpRvsNo;

    @Column(name = "LINK_ID", length = 10)
    private String linkId;

    @Column(name = "LINK_BSTP_SQNO")
    private Integer linkBstpSqno;

    @Column(name = "LINK_BSTP_DIST", precision = 7, scale = 1)
    private BigDecimal linkBstpDist;

    @Column(name = "LINK_XCORD", precision = 14, scale = 8)
    private BigDecimal linkXcord;

    @Column(name = "LINK_YCORD", precision = 14, scale = 8)
    private BigDecimal linkYcord;

    @Column(name = "ENT_JDG_DIST")
    private Integer entJdgDist;

    @Column(name = "EXT_JDG_DIST")
    private Integer extJdgDist;

    @Column(name = "DETECT_XCORD", precision = 14, scale = 8)
    private BigDecimal detectXcord;

    @Column(name = "DETECT_YCORD", precision = 14, scale = 8)
    private BigDecimal detectYcord;

    @Column(name = "BSTP_ARS")
    private Integer bstpArs;

    @Column(name = "SHLTR_TYPE", length = 3)
    private String shltrType;

    @Column(name = "RMRK", length = 300)
    private String rmrk;

    @Column(name = "VER_ID")
    private Integer verId;

    @Column(name = "UPD_DT")
    private Date updDt;

    @Column(name = "UPDUSR_ID", length = 10)
    private String updusrId;

    @Column(name = "SYNC_DT")
    private Date syncDt;

    @Column(name = "MYBI_BSTP_ID", length = 20)
    private String mybiBstpId;
}
