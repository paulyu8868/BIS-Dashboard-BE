package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.M_OP_ROUTE_POINT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface M_OP_ROUTE_POINTRepository extends JpaRepository<M_OP_ROUTE_POINT, String> {

    @Query("SELECT rp FROM M_OP_ROUTE_POINT rp JOIN M_TP_NODE np ON rp.pointId = np.pointId WHERE rp.routeId = :routeId ORDER BY rp.pointSqno")
    List<M_OP_ROUTE_POINT> findRoutePointsByRouteId(@Param("routeId") String routeId);
}
