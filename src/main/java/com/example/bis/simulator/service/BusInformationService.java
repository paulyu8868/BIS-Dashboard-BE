package com.example.bis.simulator.service;

import com.example.bis.simulator.dto.BusInformationDTO;
import com.example.bis.simulator.model.M_OP_BUS;
import com.example.bis.simulator.model.M_OP_OBU;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BusInformationService {

    @PersistenceContext
    private EntityManager entityManager;

    // OBU ID를 사용하여 BUS 및 OBU 정보로 BusInformationDTO 반환
    public Optional<BusInformationDTO> getBusInformationByObuId(String obuId) {
        // OBU 테이블에서 OBU ID로 조회
        M_OP_OBU obu = entityManager.find(M_OP_OBU.class, obuId);
        if (obu != null && obu.getBusId() != null) {
            // BUS 테이블에서 busId로 조회
            M_OP_BUS bus = entityManager.find(M_OP_BUS.class, obu.getBusId());

            if (bus != null) {
                // DTO에 필요한 정보 설정
                BusInformationDTO busInformationDTO = new BusInformationDTO();
                busInformationDTO.setBusNo(bus.getBusNo());
                busInformationDTO.setRideFixNumber(bus.getRideFixNumber());
                busInformationDTO.setInspectionDate(bus.getInspectionDate());
                busInformationDTO.setMnfctCoNm(obu.getMnfctCoNm());

                return Optional.of(busInformationDTO);
            }
        }

        return Optional.empty();
    }
}
