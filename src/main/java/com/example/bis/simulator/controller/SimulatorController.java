package com.example.bis.simulator.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimulatorController {

    // 카카오맵 API키
    @Value("${kakao.maps.api.key}")
    private String kakaoMapsApiKey;

    // 키값 simulator.html로 넘겨주기
    @GetMapping("/simulator")
    public String simulator(Model model) {
        model.addAttribute("kakaoMapsApiKey", kakaoMapsApiKey);
        return "simulator";
    }
}