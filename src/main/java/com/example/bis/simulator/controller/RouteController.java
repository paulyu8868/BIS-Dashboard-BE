package com.example.bis.simulator.controller;

import com.example.bis.simulator.dto.RouteData;
import com.example.bis.simulator.service.RouteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/route")
@CrossOrigin(origins = "http://localhost:3000") // CORS 허용
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/top10") //"최적화된 상위 10개 경로 조회", description = "최적화된 경로 데이터를 상위 10개 반환합니다.")
    public List<RouteData> getTop10Routes() {
        return routeService.getOptimizedRouteData();
    }
}
