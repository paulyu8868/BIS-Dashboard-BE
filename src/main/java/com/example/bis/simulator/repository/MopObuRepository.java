package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.M_OP_OBU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MopObuRepository extends JpaRepository<M_OP_OBU, String> {

    @Query("SELECT o.busId FROM M_OP_OBU o WHERE o.obuId = :obuId")
    String findBusIdByObuId(String obuId);
}