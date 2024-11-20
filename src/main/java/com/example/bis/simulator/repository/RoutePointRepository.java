package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.M_OP_ROUTE_POINT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutePointRepository extends JpaRepository<M_OP_ROUTE_POINT, String> {
    @Query("SELECT rp FROM M_OP_ROUTE_POINT rp WHERE rp.routeId = :routeId AND rp.pointDiv = :pointDiv ORDER BY rp.pointSqno")
    List<M_OP_ROUTE_POINT> findByRouteIdAndPointDiv(@Param("routeId") String routeId, @Param("pointDiv") String pointDiv);
}

