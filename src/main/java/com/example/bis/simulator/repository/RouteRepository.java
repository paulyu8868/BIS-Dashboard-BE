package com.example.bis.simulator.repository;

import com.example.bis.simulator.dto.BusStopDTO;
import com.example.bis.simulator.model.M_OP_ROUTE_POINT;
import com.example.bis.simulator.model.RoutePointId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<M_OP_ROUTE_POINT, RoutePointId> {

    @Query("SELECT new com.example.bis.simulator.dto.BusStopDTO(b.bstpId, b.bstpNm, b.xcord, b.ycord, rp.pointSqno) " +
            "FROM M_OP_ROUTE_POINT rp " +
            "JOIN M_TP_BSTP b ON rp.pointId = b.bstpId " +
            "WHERE rp.routeId = :routeId " +
            "ORDER BY rp.pointSqno")
    List<BusStopDTO> findBusStopsByRouteId(@Param("routeId") String routeId);
}