package com.example.bis.simulator.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "C_TC_BUS_RUNG")
public class C_TC_BUS_RUNG {

    @Id
    @Column(name = "OBU_ID", length = 10)
    private String obuId;

    @Column(name = "ROUTE_ID", length = 10)
    private String routeId;

    @Column(name = "XCORD", precision = 14, scale = 8)
    private BigDecimal xCord;

    @Column(name = "YCORD", precision = 14, scale = 8)
    private BigDecimal yCord;

    @Column(name = "PRGRSS_ANGLE", precision = 5, scale = 2)
    private BigDecimal progressAngle;

    @Column(name = "MMNT_SPD", precision = 4, scale = 1)
    private BigDecimal momentSpeed;

    @Column(name = "PASG_POINT_ID", length = 10)
    private String passagePointId;

    @Column(name = "PASG_POINT_SQNO")
    private Integer passagePointSqNo;

    @Column(name = "POINT_PASG_DT")
    private LocalDateTime pointPassageDate;

    @Column(name = "ARRVL_PLNND_POINT_ID", length = 10)
    private String arrivalPlannedPointId;

    @Column(name = "ARRVL_PLNND_POINT_SQNO")
    private Integer arrivalPlannedPointSqNo;

    @Column(name = "PASG_BSTP_ID", length = 10)
    private String passageBusStopId;

    @Column(name = "BSTP_PASG_DT")
    private LocalDateTime busStopPassageDate;

    @Column(name = "RUNG_DIV", length = 2)
    private String rungDivision;

    @Column(name = "RUNG_STTS", length = 1)
    private String rungStatus;

    @Column(name = "RUNG_STRT_DT")
    private LocalDateTime rungStartDate;

    @Column(name = "RUNG_END_DT")
    private LocalDateTime rungEndDate;

    @Column(name = "RSDL_DIST", precision = 7, scale = 1)
    private BigDecimal residualDistance;

    @Column(name = "ACML_RUNG_DIST")
    private Integer accumulatedRungDistance;

    @Column(name = "BUS_LOC_DIV", length = 10)
    private String busLocationDivision;

    @Column(name = "FRTVHC_OBU_ID", length = 10)
    private String frontVehicleObuId;

    @Column(name = "BCKVHC_OBU_ID", length = 10)
    private String backVehicleObuId;

    @Column(name = "LED_STTS", length = 1)
    private String ledStatus;

    @Column(name = "GPS_STTS", length = 1)
    private String gpsStatus;

    @Column(name = "COMM_SENSTY", length = 10)
    private String communicationSensitivity;

    @Column(name = "ROUTE_BRKAWY_YN", length = 1)
    private String routeBreakaway;

    @Column(name = "SPDNG_YN", length = 1)
    private String speeding;

    @Column(name = "UPD_DT")
    private LocalDateTime updateDate;

    @Column(name = "PASG_BSTP_SQNO")
    private Integer passageBusStopSqNo;

    // Getters and Setters
    public String getObuId() {
        return obuId;
    }

    public void setObuId(String obuId) {
        this.obuId = obuId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public BigDecimal getXCord() {
        return xCord;
    }

    public void setXCord(BigDecimal xCord) {
        this.xCord = xCord;
    }

    public BigDecimal getYCord() {
        return yCord;
    }

    public void setYCord(BigDecimal yCord) {
        this.yCord = yCord;
    }

    public BigDecimal getProgressAngle() {
        return progressAngle;
    }

    public void setProgressAngle(BigDecimal progressAngle) {
        this.progressAngle = progressAngle;
    }

    public BigDecimal getMomentSpeed() {
        return momentSpeed;
    }

    public void setMomentSpeed(BigDecimal momentSpeed) {
        this.momentSpeed = momentSpeed;
    }

    public String getPassagePointId() {
        return passagePointId;
    }

    public void setPassagePointId(String passagePointId) {
        this.passagePointId = passagePointId;
    }

    public Integer getPassagePointSqNo() {
        return passagePointSqNo;
    }

    public void setPassagePointSqNo(Integer passagePointSqNo) {
        this.passagePointSqNo = passagePointSqNo;
    }

    public LocalDateTime getPointPassageDate() {
        return pointPassageDate;
    }

    public void setPointPassageDate(LocalDateTime pointPassageDate) {
        this.pointPassageDate = pointPassageDate;
    }

    public String getArrivalPlannedPointId() {
        return arrivalPlannedPointId;
    }

    public void setArrivalPlannedPointId(String arrivalPlannedPointId) {
        this.arrivalPlannedPointId = arrivalPlannedPointId;
    }

    public Integer getArrivalPlannedPointSqNo() {
        return arrivalPlannedPointSqNo;
    }

    public void setArrivalPlannedPointSqNo(Integer arrivalPlannedPointSqNo) {
        this.arrivalPlannedPointSqNo = arrivalPlannedPointSqNo;
    }

    public String getPassageBusStopId() {
        return passageBusStopId;
    }

    public void setPassageBusStopId(String passageBusStopId) {
        this.passageBusStopId = passageBusStopId;
    }

    public LocalDateTime getBusStopPassageDate() {
        return busStopPassageDate;
    }

    public void setBusStopPassageDate(LocalDateTime busStopPassageDate) {
        this.busStopPassageDate = busStopPassageDate;
    }

    public String getRungDivision() {
        return rungDivision;
    }

    public void setRungDivision(String rungDivision) {
        this.rungDivision = rungDivision;
    }

    public String getRungStatus() {
        return rungStatus;
    }

    public void setRungStatus(String rungStatus) {
        this.rungStatus = rungStatus;
    }

    public LocalDateTime getRungStartDate() {
        return rungStartDate;
    }

    public void setRungStartDate(LocalDateTime rungStartDate) {
        this.rungStartDate = rungStartDate;
    }

    public LocalDateTime getRungEndDate() {
        return rungEndDate;
    }

    public void setRungEndDate(LocalDateTime rungEndDate) {
        this.rungEndDate = rungEndDate;
    }

    public BigDecimal getResidualDistance() {
        return residualDistance;
    }

    public void setResidualDistance(BigDecimal residualDistance) {
        this.residualDistance = residualDistance;
    }

    public Integer getAccumulatedRungDistance() {
        return accumulatedRungDistance;
    }

    public void setAccumulatedRungDistance(Integer accumulatedRungDistance) {
        this.accumulatedRungDistance = accumulatedRungDistance;
    }

    public String getBusLocationDivision() {
        return busLocationDivision;
    }

    public void setBusLocationDivision(String busLocationDivision) {
        this.busLocationDivision = busLocationDivision;
    }

    public String getFrontVehicleObuId() {
        return frontVehicleObuId;
    }

    public void setFrontVehicleObuId(String frontVehicleObuId) {
        this.frontVehicleObuId = frontVehicleObuId;
    }

    public String getBackVehicleObuId() {
        return backVehicleObuId;
    }

    public void setBackVehicleObuId(String backVehicleObuId) {
        this.backVehicleObuId = backVehicleObuId;
    }

    public String getLedStatus() {
        return ledStatus;
    }

    public void setLedStatus(String ledStatus) {
        this.ledStatus = ledStatus;
    }

    public String getGpsStatus() {
        return gpsStatus;
    }

    public void setGpsStatus(String gpsStatus) {
        this.gpsStatus = gpsStatus;
    }

    public String getCommunicationSensitivity() {
        return communicationSensitivity;
    }

    public void setCommunicationSensitivity(String communicationSensitivity) {
        this.communicationSensitivity = communicationSensitivity;
    }

    public String getRouteBreakaway() {
        return routeBreakaway;
    }

    public void setRouteBreakaway(String routeBreakaway) {
        this.routeBreakaway = routeBreakaway;
    }

    public String getSpeeding() {
        return speeding;
    }

    public void setSpeeding(String speeding) {
        this.speeding = speeding;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getPassageBusStopSqNo() {
        return passageBusStopSqNo;
    }
}

