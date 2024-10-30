package com.example.bis.simulator.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "M_TP_LINK")
public class M_TP_LINK{

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

    // Getters and Setters
    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public Integer getLaneCnt() {
        return laneCnt;
    }

    public void setLaneCnt(Integer laneCnt) {
        this.laneCnt = laneCnt;
    }

    public String getLinkNm() {
        return linkNm;
    }

    public void setLinkNm(String linkNm) {
        this.linkNm = linkNm;
    }

    public String getRoadGrd() {
        return roadGrd;
    }

    public void setRoadGrd(String roadGrd) {
        this.roadGrd = roadGrd;
    }

    public String getRoadType() {
        return roadType;
    }

    public void setRoadType(String roadType) {
        this.roadType = roadType;
    }

    public String getRoadNo() {
        return roadNo;
    }

    public void setRoadNo(String roadNo) {
        this.roadNo = roadNo;
    }

    public BigDecimal getLen() {
        return len;
    }

    public void setLen(BigDecimal len) {
        this.len = len;
    }

    public String getStrtNodeId() {
        return strtNodeId;
    }

    public void setStrtNodeId(String strtNodeId) {
        this.strtNodeId = strtNodeId;
    }

    public String getEndNodeId() {
        return endNodeId;
    }

    public void setEndNodeId(String endNodeId) {
        this.endNodeId = endNodeId;
    }

    public String getRgsphCd() {
        return rgsphCd;
    }

    public void setRgsphCd(String rgsphCd) {
        this.rgsphCd = rgsphCd;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getMltsctnYn() {
        return mltsctnYn;
    }

    public void setMltsctnYn(String mltsctnYn) {
        this.mltsctnYn = mltsctnYn;
    }

    public String getCntrdCd() {
        return cntrdCd;
    }

    public void setCntrdCd(String cntrdCd) {
        this.cntrdCd = cntrdCd;
    }

    public BigDecimal getHghstLimitSpd() {
        return hghstLimitSpd;
    }

    public void setHghstLimitSpd(BigDecimal hghstLimitSpd) {
        this.hghstLimitSpd = hghstLimitSpd;
    }

    public String getPassLimitVhcl() {
        return passLimitVhcl;
    }

    public void setPassLimitVhcl(String passLimitVhcl) {
        this.passLimitVhcl = passLimitVhcl;
    }

    public Integer getPassLimitWeight() {
        return passLimitWeight;
    }

    public void setPassLimitWeight(Integer passLimitWeight) {
        this.passLimitWeight = passLimitWeight;
    }

    public Integer getPassLimitHeight() {
        return passLimitHeight;
    }

    public void setPassLimitHeight(Integer passLimitHeight) {
        this.passLimitHeight = passLimitHeight;
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
