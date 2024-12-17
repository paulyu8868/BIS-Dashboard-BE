package com.example.bis.simulator.repository;

import com.example.bis.simulator.dto.BusDataResponse;
import com.example.bis.simulator.dto.BusSimulationResponse;
import com.example.bis.simulator.dto.VertexDTO;
import com.example.bis.simulator.model.C_TC_BUS_RUNG;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRungRepository extends JpaRepository<C_TC_BUS_RUNG, String> {

    /**
     * 노선 ID에 해당하는 버스 정보 조회
     */
    @Query("""
        SELECT new com.example.bis.simulator.dto.BusDataResponse(
            bus.busId, bus.busNo, busRung.obuId
        )
        FROM C_TC_BUS_RUNG busRung
        JOIN M_OP_OBU obu ON busRung.obuId = obu.obuId
        JOIN M_OP_BUS bus ON obu.busId = bus.busId
        WHERE busRung.routeId = :routeId
    """)
    List<BusDataResponse> findBusDetailsByRouteId(String routeId);

    /**
     * 노선 ID와 상태가 1인 버스의 실시간 위치 조회
     */
    @Query("""
        SELECT new com.example.bis.simulator.dto.BusSimulationResponse(
            busRung.obuId,
            busRung.routeId,
            busRung.xCord,
            busRung.yCord,
            busRung.momentSpeed,
            busRung.passagePointId,
            busRung.passagePointSqNo,
            busRung.pointPassageDate,
            busRung.arrivalPlannedPointId,
            busRung.arrivalPlannedPointSqNo,
            busRung.busLocationDivision
        )
        FROM C_TC_BUS_RUNG busRung
        WHERE busRung.routeId = :routeId
          AND busRung.rungStatus = '1'
    """)
    List<BusSimulationResponse> findBusLocationsByRouteId(String routeId);

    /**
     * 특정 OBU ID의 상태를 1로 변경
     */
    @Modifying
    @Query("""
        UPDATE C_TC_BUS_RUNG
        SET rungStatus = '1'
        WHERE obuId = :obuId
    """)
    void updateBusStatus(String obuId);

    @Modifying
    @Query("""
        UPDATE C_TC_BUS_RUNG
        SET rungStatus = '0'
        WHERE obuId = :obuId
    """)
    void updateBusNotRun(String obuId);

    /**
     * 상태에 해당하는 모든 버스 조회
     */
    List<C_TC_BUS_RUNG> findByRungStatus(String rungStatus);

    /**
     * 노선 ID와 상태에 해당하는 버스 조회
     */
    List<C_TC_BUS_RUNG> findByRouteIdAndRungStatus(String routeId, String rungStatus);

    /**
     * 특정 OBU ID의 현재 좌표를 반환하는 메서드
     */
    @Query("""
        SELECT busRung.xCord, busRung.yCord
        FROM C_TC_BUS_RUNG busRung
        WHERE busRung.obuId = :obuId
    """)
    List<Object[]> findBusCoordinatesByObuId(String obuId);

    @Modifying
    @Query("UPDATE C_TC_BUS_RUNG r SET r.xCord = :xcord, r.yCord = :ycord WHERE r.obuId = :obuId")
    void updateInitialLocation(@Param("obuId") String obuId,
                               @Param("xcord") BigDecimal xcord,
                               @Param("ycord") BigDecimal ycord);

}
