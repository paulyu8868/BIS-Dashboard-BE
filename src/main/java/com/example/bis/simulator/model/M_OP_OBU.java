package com.example.bis.simulator.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "M_OP_OBU")
public class M_OP_OBU {

    @Id
    @Column(name = "obu_id", length = 10)
    private String obuId;

    @Column(name = "bus_id")
    private Integer busId;

    @Column(name = "mnfct_co_nm", length = 30)
    private String mnfctCoNm;

    @Column(name = "prdct_no", length = 18)
    private String prdctNo;

    @Column(name = "buy_ymd")
    @Temporal(TemporalType.DATE)
    private Date buyYmd;

    @Column(name = "info_upd_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date infoUpdDt;

    @Column(name = "use_yn", length = 1)
    private String useYn;

    @Column(name = "crt_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crtDt;

    @Column(name = "upd_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updDt;

    // 기본 생성자
    public M_OP_OBU() {
    }

    // Getters와 Setters
    public String getObuId() {
        return obuId;
    }

    public void setObuId(String obuId) {
        this.obuId = obuId;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public String getMnfctCoNm() {
        return mnfctCoNm;
    }

    public void setMnfctCoNm(String mnfctCoNm) {
        this.mnfctCoNm = mnfctCoNm;
    }

    public String getPrdctNo() {
        return prdctNo;
    }

    public void setPrdctNo(String prdctNo) {
        this.prdctNo = prdctNo;
    }

    public Date getBuyYmd() {
        return buyYmd;
    }

    public void setBuyYmd(Date buyYmd) {
        this.buyYmd = buyYmd;
    }

    public Date getInfoUpdDt() {
        return infoUpdDt;
    }

    public void setInfoUpdDt(Date infoUpdDt) {
        this.infoUpdDt = infoUpdDt;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }

    public Date getUpdDt() {
        return updDt;
    }

    public void setUpdDt(Date updDt) {
        this.updDt = updDt;
    }
}

