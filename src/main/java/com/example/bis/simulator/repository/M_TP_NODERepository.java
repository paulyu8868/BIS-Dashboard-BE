package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.M_TP_NODE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface M_TP_NODERepository extends JpaRepository<M_TP_NODE, Integer> {
}
