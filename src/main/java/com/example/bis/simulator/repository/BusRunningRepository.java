package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.C_TC_BUS_RUNG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BusRunningRepository extends JpaRepository<C_TC_BUS_RUNG, String> {


    // 버스 현재위치 조회 기능
    @Query("SELECT b FROM C_TC_BUS_RUNG b WHERE b.routeId = :routeId AND b.rungStatus = '1'")
    List<C_TC_BUS_RUNG> findAllRunningBusesByRouteId(@Param("routeId") String routeId);

    /*
    버스 운행상태 변경기능
     */
    // 해당 노선의 모든 버스 운행 상태 0 (=운행 중지)
    @Modifying
    @Query("UPDATE C_TC_BUS_RUNG b SET b.rungStatus = '0' WHERE b.routeId = :routeId")
    int stopAllBusesOnRoute(@Param("routeId") String routeId);

    // 해당 버스를 운행상태로 변경
    @Modifying
    @Query("UPDATE C_TC_BUS_RUNG b SET b.rungStatus = '1' WHERE b.obuId = :obuId")
    int startBusOperation(@Param("obuId") String obuId);
}