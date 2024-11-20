package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.M_TP_BSTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusStopRepository extends JpaRepository<M_TP_BSTP, String> {
    @Query("SELECT b FROM M_TP_BSTP b WHERE b.bstpId IN :bstpIds")
    List<M_TP_BSTP> findByBstpIds(@Param("bstpIds") List<String> bstpIds);
}

