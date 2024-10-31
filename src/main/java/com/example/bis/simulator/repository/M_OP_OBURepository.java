package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.M_OP_OBU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface M_OP_OBURepository extends JpaRepository<M_OP_OBU, Integer> {
}

