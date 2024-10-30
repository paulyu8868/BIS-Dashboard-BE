package com.example.bis.simulator.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "M_TP_NODE")
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

    // Getters and Setters
    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeNm() {
        return nodeNm;
    }

    public void setNodeNm(String nodeNm) {
        this.nodeNm = nodeNm;
    }

    public String getNodeSnm() {
        return nodeSnm;
    }

    public void setNodeSnm(String nodeSnm) {
        this.nodeSnm = nodeSnm;
    }

    public BigDecimal getXcord() {
        return xcord;
    }

    public void setXcord(BigDecimal xcord) {
        this.xcord = xcord;
    }

    public BigDecimal getYcord() {
        return ycord;
    }

    public void setYcord(BigDecimal ycord) {
        this.ycord = ycord;
    }

    public String getAdstdgCd() {
        return adstdgCd;
    }

    public void setAdstdgCd(String adstdgCd) {
        this.adstdgCd = adstdgCd;
    }

    public String getRgsphCd() {
        return rgsphCd;
    }

    public void setRgsphCd(String rgsphCd) {
        this.rgsphCd = rgsphCd;
    }

    public BigDecimal getEntJdgDist() {
        return entJdgDist;
    }

    public void setEntJdgDist(BigDecimal entJdgDist) {
        this.entJdgDist = entJdgDist;
    }

    public BigDecimal getExtJdgDist() {
        return extJdgDist;
    }

    public void setExtJdgDist(BigDecimal extJdgDist) {
        this.extJdgDist = extJdgDist;
    }

    public String getRttnLimitYn() {
        return rttnLimitYn;
    }

    public void setRttnLimitYn(String rttnLimitYn) {
        this.rttnLimitYn = rttnLimitYn;
    }

    public String getRmrk() {
        return rmrk;
    }

    public void setRmrk(String rmrk) {
        this.rmrk = rmrk;
    }

    public Integer getVerId() {
        return verId;
    }

    public void setVerId(Integer verId) {
        this.verId = verId;
    }

    public Date getUpdDt() {
        return updDt;
    }

    public void setUpdDt(Date updDt) {
        this.updDt = updDt;
    }

    public String getUpdusrId() {
        return updusrId;
    }

    public void setUpdusrId(String updusrId) {
        this.updusrId = updusrId;
    }

    public Date getSyncDt() {
        return syncDt;
    }

    public void setSyncDt(Date syncDt) {
        this.syncDt = syncDt;
    }
}

