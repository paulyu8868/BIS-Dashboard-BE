package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.C_TC_BUS_RUNG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CtcBusRungRepository extends JpaRepository<C_TC_BUS_RUNG, String> {

    @Query("SELECT DISTINCT c.obuId FROM C_TC_BUS_RUNG c WHERE c.routeId IN (:routeIds)")
    List<String> findObuIdsByRouteIds(List<String> routeIds);

    @Query("SELECT DISTINCT c.routeId FROM C_TC_BUS_RUNG c WHERE c.obuId IN (SELECT m.obuId FROM M_OP_OBU m WHERE m.busId = :busId)")
    List<String> findRouteIdsByBusId(String busId);
}
