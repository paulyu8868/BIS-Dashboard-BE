package com.example.bis.simulator.service;

import com.example.bis.simulator.dto.BusLocationDTO;
import com.example.bis.simulator.model.C_TC_BUS_RUNG;
import com.example.bis.simulator.repository.BusRungRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusRungService {

    @Autowired
    private BusRungRepository repository;

    // RUNG_STTS가 '1'인 데이터들만(운행중인 버스만) 필터링하여 OBU_ID와 PASG_POINT_ID를 반환
    public List<BusLocationDTO> getObuAndPassagePointIds(String routeId) {
        List<C_TC_BUS_RUNG> filteredData = repository.findByRouteIdAndRungStatus(routeId, "1");

        // 필요한 데이터만 반환 (pointSqno, obuId)
        return filteredData.stream()
                .map(data -> new BusLocationDTO(data.getPassagePointSqNo(), data.getObuId()))
                .collect(Collectors.toList());
    }
}
