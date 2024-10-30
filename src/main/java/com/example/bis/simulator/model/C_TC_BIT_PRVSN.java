package com.example.bis.simulator.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "C_TC_BIT_PRVSN")
public class C_TC_BIT_PRVSN {

    @Id
    @Column(name = "bit_id", length = 10)
    private String bitId;

    @Column(name = "prvsn_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prvsnDt;

    @Column(name = "prvsn_msg")
    private String prvsnMsg;

    // 기본 생성자
    public C_TC_BIT_PRVSN() {
    }

    // Getters와 Setters
    public String getBitId() {
        return bitId;
    }

    public void setBitId(String bitId) {
        this.bitId = bitId;
    }

    public Date getPrvsnDt() {
        return prvsnDt;
    }

    public void setPrvsnDt(Date prvsnDt) {
        this.prvsnDt = prvsnDt;
    }

    public String getPrvsnMsg() {
        return prvsnMsg;
    }

    public void setPrvsnMsg(String prvsnMsg) {
        this.prvsnMsg = prvsnMsg;
    }
}

