package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.M_OP_ROUTE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface M_OP_ROUTERepository extends JpaRepository<M_OP_ROUTE, Integer> {
    // 필요 시 추가 쿼리 메서드 작성
}
