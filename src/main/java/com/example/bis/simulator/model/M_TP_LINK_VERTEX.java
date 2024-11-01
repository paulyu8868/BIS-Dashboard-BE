package com.example.bis.simulator.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "M_TP_LINK_VERTEX")
@Data
@NoArgsConstructor // 기본 생성자 생성

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
