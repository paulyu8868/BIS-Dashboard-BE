package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.M_TP_LINK_VERTEX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkVertexRepository extends JpaRepository<M_TP_LINK_VERTEX, String> {
    @Query("SELECT lv FROM M_TP_LINK_VERTEX lv WHERE lv.linkId IN :linkIds ORDER BY lv.linkId, lv.sqno")
    List<M_TP_LINK_VERTEX> findByLinkIds(@Param("linkIds") List<String> linkIds);
}


