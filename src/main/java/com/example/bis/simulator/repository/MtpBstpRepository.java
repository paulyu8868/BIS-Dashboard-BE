package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.M_TP_BSTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MtpBstpRepository extends JpaRepository<M_TP_BSTP, String> {
    M_TP_BSTP findByBstpId(String bstpId);
}
