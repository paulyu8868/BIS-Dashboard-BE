package com.example.bis.simulator.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "C_TC_BIT_PRVSN")
@Data
public class C_TC_BIT_PRVSN {

    @Id
    @Column(name = "bit_id", length = 10)
    private String bitId;

    @Column(name = "prvsn_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prvsnDt;

    @Column(name = "prvsn_msg")
    private String prvsnMsg;

    // 기본 생성자는 Lombok에 의해 자동 생성됨
}
