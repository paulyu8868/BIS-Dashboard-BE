package com.example.bis.simulator.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimulatorController {

    @Value("${kakao.maps.api.key}")
    private String kakaoMapsApiKey;

    @GetMapping("/simulator")
    public String simulator(Model model) {
        model.addAttribute("kakaoMapsApiKey", kakaoMapsApiKey);
        return "simulator";
    }
}