package com.example.bis.simulator.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "M_TP_LINK_VERTEX")
@Data
@NoArgsConstructor
@IdClass(LinkVertexId.class)
public class M_TP_LINK_VERTEX {

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
}
