package com.example.bis.simulator.service;

import com.example.bis.simulator.dto.BusStopDTO;
import com.example.bis.simulator.dto.VertexDTO;
import com.example.bis.simulator.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;

    /**
     * 특정 노선의 정류장 데이터를 동적으로 조회합니다.
     *
     * @param routeId 노선 ID
     * @return BusStopDTO 리스트
     */
    public List<BusStopDTO> getBusStops(String routeId) {
        return routeRepository.findBusStopsByRouteId(routeId);
    }

    /**
     * 특정 노선의 모든 버텍스 조회
     *
     * @param routeId 노선 ID
     * @return VertexDTO 리스트
     */
    public List<VertexDTO> getRouteVertexes(String routeId) {
        return routeRepository.findVertexesByRouteId(routeId);
    }
}
