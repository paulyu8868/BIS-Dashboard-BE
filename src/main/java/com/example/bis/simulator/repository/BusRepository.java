package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.M_OP_BUS;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * M_OP_BUS 테이블과 상호작용하는 Repository.
 */
@Repository
public interface BusRepository extends JpaRepository<M_OP_BUS, String> {

    /**
     * 상위 10개의 버스 정보를 조회합니다.
     *
     * @param pageable 페이징 객체
     * @return 상위 10개의 M_OP_BUS 리스트
     */
    Page<M_OP_BUS> findAll(Pageable pageable);
}
