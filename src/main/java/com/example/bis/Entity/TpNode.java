package com.example.bis.Entity;

import lombok.Data;
import jakarta.persistence.*;


@Entity
@Table(name = "M_TP_NODE")
@Data // Lombok의 @Data 어노테이션을 사용하여 Getter, Setter, toString, equals, hashCode를 자동 생성
public class TpNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nodeId;

    @Column(name = "NODE_TYPE")
    private String nodeType;

    @Column(name = "NODE_NM")
    private String nodeName;

    @Column(name = "NODE_SNM")
    private String nodeShortName;

    @Column(name = "XCORD")
    private Double xCord;

    @Column(name = "YCORD")
    private Double yCord;

    @Column(name = "ADSTDG_CD")
    private String adstdgCd;

    @Column(name = "RGSPH_CD")
    private String rgspHcd;

    @Column(name = "ENT_JDG_DIST")
    private String entJdgDist;

    @Column(name = "EXT_JDG_DIST")
    private String extJdgDist;

    @Column(name = "RTTN_LIMIT_YN")
    private String rttnLimitYn;

    @Column(name = "RMRK")
    private String rmrk;

    @Column(name = "VER_ID")
    private String verId;

    @Column(name = "UPD_DT")
    private String updDt;

    @Column(name = "UPDUSR_ID")
    private String updusrId;

    @Column(name = "SYNC_DT")
    private String syncDt;
}

