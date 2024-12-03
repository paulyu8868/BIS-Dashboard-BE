package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.C_TC_BUS_RUNG;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * C_TC_BUS_RUNG 테이블과 상호작용하는 Repository.
 */
@Repository
public interface BusRungRepository extends JpaRepository<C_TC_BUS_RUNG, String> {
    List<C_TC_BUS_RUNG> findByRouteIdAndRungStatus(String routeId, String rungStatus);
}
