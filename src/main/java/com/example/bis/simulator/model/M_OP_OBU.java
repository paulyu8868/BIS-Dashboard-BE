package com.example.bis.simulator.model;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "M_OP_OBU")
@Data  // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
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

    // 기본 생성자는 Lombok에 의해 자동 생성됨
}
