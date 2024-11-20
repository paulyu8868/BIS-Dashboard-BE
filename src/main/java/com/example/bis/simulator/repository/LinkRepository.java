package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.M_TP_LINK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<M_TP_LINK, String> {
    @Query("SELECT l FROM M_TP_LINK l WHERE l.strtNodeId IN :startNodeIds AND l.endNodeId IN :endNodeIds")
    List<M_TP_LINK> findByStartAndEndNodeIds(@Param("startNodeIds") List<String> startNodeIds, @Param("endNodeIds") List<String> endNodeIds);
}


