package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.M_OP_BUS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MopBusRepository extends JpaRepository<M_OP_BUS, String> {

    @Query("SELECT b.busNo FROM M_OP_BUS b WHERE b.busId = :busId")
    String findBusNoByBusId(String busId);
}
