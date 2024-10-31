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
    @Column(name = "OBU_ID", length = 10)
    private String obuId;

    @Column(name = "BUS_ID")
    private Integer busId;

    @Column(name = "MNFCT_CO_NM", length = 30)
    private String mnfctCoNm;

    @Column(name = "PRDCT_NO", length = 18)
    private String prdctNo;

    @Column(name = "BUY_YMD")
    @Temporal(TemporalType.DATE)
    private Date buyYmd;

    @Column(name = "INFO_UPD_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date infoUpdDt;

    @Column(name = "USE_YN", length = 1)
    private String useYn;

    @Column(name = "CRT_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crtDt;

    @Column(name = "UPD_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updDt;

    // 기본 생성자는 Lombok에 의해 자동 생성됨
}
