package com.example.bis.simulator.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "M_TP_LINK_VERTEX")
public class M_TP_LINK_VERTEX implements Serializable {

    @Id
    @Column(name = "LINK_ID", length = 10, nullable = false)
    private String linkId;

    @Id
    @Column(name = "SQNO", nullable = false)
    private Integer sqno;

    @Column(name = "XCORD", precision = 14, scale = 8)
    private BigDecimal xcord;

    @Column(name = "YCORD", precision = 14, scale = 8)
    private BigDecimal ycord;

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

    // Getters and Setters
    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public Integer getSqno() {
        return sqno;
    }

    public void setSqno(Integer sqno) {
        this.sqno = sqno;
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

    // equals() 및 hashCode() 메서드 구현
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof M_TP_LINK_VERTEX)) return false;
        M_TP_LINK_VERTEX that = (M_TP_LINK_VERTEX) o;
        return Objects.equals(linkId, that.linkId) && Objects.equals(sqno, that.sqno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkId, sqno);
    }
}
