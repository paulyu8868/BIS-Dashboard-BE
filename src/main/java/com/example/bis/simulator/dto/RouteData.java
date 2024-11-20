package com.example.bis.simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class RouteData {
    private String routeId;         // 노선 ID
    private String routeName;       // 노선 이름
    private List<LinkData> links;   // 경로 링크 데이터 (폴리라인)
    private List<BusStopData> busStops; // 정류장 데이터
}
