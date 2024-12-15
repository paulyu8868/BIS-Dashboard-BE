package com.example.bis.simulator.repository;

import com.example.bis.simulator.dto.BusStopDTO;
import com.example.bis.simulator.dto.VertexDTO;
import com.example.bis.simulator.model.M_OP_ROUTE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<M_OP_ROUTE, String> {
    /*
    ROUTE_ID 로 조회
    개별조회
     */
    // 정류장 조회
    @Query("SELECT new com.example.bis.simulator.dto.BusStopDTO(b.bstpId, b.bstpNm, b.xcord, b.ycord, rp.pointSqno) " +
            "FROM M_OP_ROUTE_POINT rp " +
            "JOIN M_TP_BSTP b ON rp.pointId = b.bstpId " +
            "WHERE rp.routeId = :routeId " +
            "ORDER BY rp.pointSqno")
    List<BusStopDTO> findBusStopsByRouteId(@Param("routeId") String routeId);


    // 모든 VERTEX 조회
    // 버텍스 순서 = (지점 포인트 순서,버텍스 순서)로 정렬
    @Query("SELECT new com.example.bis.simulator.dto.VertexDTO(" +
            "bs.linkId, rp.pointSqno, v.sqno, v.xcord, v.ycord) " +
            "FROM M_OP_ROUTE_POINT rp " +
            "JOIN M_TP_BSTP bs ON rp.pointId = bs.bstpId " +
            "JOIN M_TP_LINK_VERTEX v ON bs.linkId = v.linkId " +
            "WHERE rp.routeId = :routeId " +
            "ORDER BY rp.pointSqno, v.sqno")
    List<VertexDTO> findVertexesByRouteId(@Param("routeId") String routeId);
}
