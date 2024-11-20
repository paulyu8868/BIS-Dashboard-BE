package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.M_OP_ROUTE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<M_OP_ROUTE, String> {
    @Query("SELECT r.routeId FROM M_OP_ROUTE r ORDER BY r.updateDate DESC")
    List<String> findTop10RouteIds(Pageable pageable);
}
