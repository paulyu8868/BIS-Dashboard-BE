package com.example.bis.simulator.controller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;

@Configuration
@Getter
public class KakaoMapsConfig {

    @Value("${kakao.maps.api.key}")
    private String apiKey;
}