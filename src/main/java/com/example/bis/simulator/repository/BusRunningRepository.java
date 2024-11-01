package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.C_TC_BUS_RUNG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BusRunningRepository extends JpaRepository<C_TC_BUS_RUNG, String> {

    @Query("SELECT b FROM C_TC_BUS_RUNG b WHERE b.obuId = :obuId AND b.routeId = :routeId AND b.rungStatus = '1'")
    Optional<C_TC_BUS_RUNG> findByObuIdAndRouteId(
            @Param("obuId") String obuId,
            @Param("routeId") String routeId
    );
}