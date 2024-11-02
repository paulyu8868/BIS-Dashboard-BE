package com.example.bis.simulator.service;

import com.example.bis.simulator.dto.BusStopDTO;
import com.example.bis.simulator.dto.NodeDTO;
import com.example.bis.simulator.dto.VertexDTO;
import com.example.bis.simulator.model.M_TP_LINK_VERTEX;
import com.example.bis.simulator.model.M_TP_NODE;
import com.example.bis.simulator.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {

    private static final Logger logger = LoggerFactory.getLogger(RouteService.class);
    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }


    // 정류장 정보 조회
    @Transactional(readOnly = true)
    public List<BusStopDTO> getBusStops(String routeId) {
        logger.info("정류장 정보 조회 시작 - routeId: {}", routeId);
        List<BusStopDTO> busStops = routeRepository.findBusStopsByRouteId(routeId);
        logger.info("정류장 정보 조회 완료 - routeId: {}, 정류장 수: {}", routeId, busStops.size());
        return busStops;
    }

    // 노선에 있는 노드 정보 조회
    @Transactional(readOnly = true)
    public List<NodeDTO> getRouteNodes(String routeId) {
        try {
            List<M_TP_NODE> nodes = routeRepository.findAllNodesByRouteId(routeId);

            return nodes.stream()
                    .map(node -> NodeDTO.builder()
                            .nodeId(node.getNodeId())
                            .nodeType(node.getNodeType())
                            .xcord(node.getXcord())
                            .ycord(node.getYcord())
                            .build())
                    .collect(Collectors.toList());

        } catch (Exception e) {
            logger.error("노드 정보 조회 실패 - routeId: " + routeId, e);
            throw new RuntimeException("노드 정보 조회 실패", e);
        }
    }

    // 노선에 있는 모든 버텍스 조회
    @Transactional(readOnly = true)
    public List<VertexDTO> getRouteVertexes(String routeId) {
        logger.info("노선 버텍스 조회 시작 - routeId: {}", routeId);
        List<VertexDTO> vertexes = routeRepository.findVertexesByRouteId(routeId);
        logger.info("노선 버텍스 조회 완료 - routeId: {}, 버텍스 수: {}", routeId, vertexes.size());
        return vertexes;
    }
}