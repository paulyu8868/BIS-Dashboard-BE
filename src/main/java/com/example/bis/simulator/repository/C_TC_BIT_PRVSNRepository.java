package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.C_TC_BIT_PRVSN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface C_TC_BIT_PRVSNRepository extends JpaRepository<C_TC_BIT_PRVSN, String> {
}