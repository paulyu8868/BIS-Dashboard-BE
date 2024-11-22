package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.M_OP_OBU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * M_OP_OBU 테이블과 상호작용하는 Repository.
 */
@Repository
public interface ObuRepository extends JpaRepository<M_OP_OBU, String> {

    /**
     * 특정 BUS_ID에 해당하는 OBU 정보를 조회합니다.
     * @param busId BUS_ID
     * @return M_OP_OBU 객체
     */
    M_OP_OBU findByBusId(Integer busId);
}
