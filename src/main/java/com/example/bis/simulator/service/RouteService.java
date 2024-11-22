package com.example.bis.simulator.service;

import com.example.bis.simulator.dto.*;
import com.example.bis.simulator.model.*;
import com.example.bis.simulator.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final RoutePointRepository routePointRepository;
    private final LinkRepository linkRepository;
    private final LinkVertexRepository linkVertexRepository;
    private final BusStopRepository busStopRepository;

    public List<RouteData> getOptimizedRouteData() {
        // 1. 상위 10개 노선 ID 조회
        List<String> routeIds = routeRepository.findTop10RouteIds(PageRequest.of(0, 10));
        List<RouteData> routeDataList = new ArrayList<>();

        // 노선 번호를 1번부터 순서대로 매핑
        for (int index = 0; index < routeIds.size(); index++) {
            String routeId = routeIds.get(index);

            // 2. 노선 링크 데이터 조회
            List<M_OP_ROUTE_POINT> nodePoints = routePointRepository.findByRouteIdAndPointDiv(routeId, "0");
            List<String> startNodeIds = new ArrayList<>();
            List<String> endNodeIds = new ArrayList<>();

            for (int i = 0; i < nodePoints.size() - 1; i++) {
                startNodeIds.add(nodePoints.get(i).getPointId());
                endNodeIds.add(nodePoints.get(i + 1).getPointId());
            }

            List<M_TP_LINK> links = linkRepository.findByStartAndEndNodeIds(startNodeIds, endNodeIds);
            List<String> linkIds = links.stream().map(M_TP_LINK::getLinkId).collect(Collectors.toList());
            List<M_TP_LINK_VERTEX> linkVertices = linkVertexRepository.findByLinkIds(linkIds);

            // 링크 데이터를 LinkData DTO로 매핑
            List<LinkData> linkDataList = links.stream()
                    .map(link -> {
                        List<LinkVertexData> vertices = linkVertices.stream()
                                .filter(vertex -> vertex.getLinkId().equals(link.getLinkId()))
                                .map(vertex -> new LinkVertexData(
                                        vertex.getXcord(),
                                        vertex.getYcord()
                                ))
                                .collect(Collectors.toList());
                        return new LinkData(link.getLinkId(), vertices);
                    })
                    .collect(Collectors.toList());

            // 3. 정류장 데이터 조회
            List<M_OP_ROUTE_POINT> busStopPoints = routePointRepository.findByRouteIdAndPointDiv(routeId, "1");
            busStopPoints.sort(Comparator.comparing(M_OP_ROUTE_POINT::getPointSqno)); // 정류장 순번으로 정렬
            List<String> busStopIds = busStopPoints.stream()
                    .map(M_OP_ROUTE_POINT::getPointId)
                    .collect(Collectors.toList());

            List<M_TP_BSTP> busStops = busStopRepository.findByBstpIds(busStopIds);

            // 정류장 데이터를 BusStopData DTO로 매핑
            List<BusStopData> busStopDataList = busStops.stream()
                    .map(stop -> new BusStopData(
                            stop.getBstpId(),
                            stop.getBstpNm(),
                            stop.getXcord(),
                            stop.getYcord()
                    ))
                    .collect(Collectors.toList());

            // 4. RouteData 매핑 (노선 이름: "1번 노선", "2번 노선", ...)
            RouteData routeData = new RouteData(
                    routeId,
                    (index + 1) + "번 노선", // 노선 이름
                    linkDataList,           // 경로 데이터
                    busStopDataList         // 정류장 데이터
            );

            routeDataList.add(routeData);
        }

        return routeDataList;
    }
}
