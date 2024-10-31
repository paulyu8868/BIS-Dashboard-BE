package com.example.bis.simulator.service;

import com.example.bis.simulator.dto.BusDataDTO;
import com.example.bis.simulator.model.M_OP_OBU;
import com.example.bis.simulator.model.M_OP_ROUTE;
import com.example.bis.simulator.model.M_TP_NODE;
import com.example.bis.simulator.repository.M_OP_OBURepository;
import com.example.bis.simulator.repository.M_OP_ROUTERepository;
import com.example.bis.simulator.repository.M_TP_NODERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class BusSimulatorService {

    @Autowired
    private M_OP_OBURepository obuRepository;

    @Autowired
    private M_TP_NODERepository nodeRepository;

    @Autowired
    private M_OP_ROUTERepository routeRepository;

    private static final String COLLECTION_SERVER_URL = "http://localhost:8080/api/busdata";
    private static final long SIMULATION_INTERVAL = 5000;

    private BigDecimal previousXCoord;
    private BigDecimal previousYCoord;
    private LocalDateTime previousTime;

    public void simulateBusMovement(String busId, String routeId) {
        M_OP_OBU obu = obuRepository.findById(Integer.valueOf(busId))
                .orElseThrow(() -> new RuntimeException("버스를 찾을 수 없습니다."));

        M_OP_ROUTE route = routeRepository.findById(Integer.valueOf(routeId))
                .orElseThrow(() -> new RuntimeException("노선 정보를 찾을 수 없습니다."));

        BusDataDTO busData = new BusDataDTO();

        // 현재 날짜 및 시간 설정
        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        busData.setColDate(currentDate);

        busData.setBusId(busId);

        // 임시 정류장 ID 및 위치 설정
        String presttId = "22222";
        String nextsttId = "33333";
        busData.setPresttId(presttId);
        busData.setNextsttId(nextsttId);

        BigDecimal xCoord = new BigDecimal(126.57486667);
        BigDecimal yCoord = new BigDecimal(35.94681667);

        // 위도와 경도 저장
        busData.setLongitude(xCoord.doubleValue());
        busData.setLatitude(yCoord.doubleValue());

        // 속도 계산 및 설정
        Double speed = calculateSpeed(xCoord, yCoord);
        busData.setSpeed(speed);

        // 이벤트 코드 계산
        String eventCode = calculateEventCode(presttId, nextsttId, xCoord, yCoord);
        busData.setEventCode(eventCode);

        // 방위각 (임의 값 설정)
        busData.setAzimuth(90.0);

        sendDataToCollectionServer(busData);
    }

    private Double calculateSpeed(BigDecimal xCoord, BigDecimal yCoord) {
        if (previousXCoord == null || previousYCoord == null || previousTime == null) {
            previousXCoord = xCoord;
            previousYCoord = yCoord;
            previousTime = LocalDateTime.now();
            return 0.0;
        }

        double earthRadius = 6371000;
        double dLat = Math.toRadians(yCoord.doubleValue() - previousYCoord.doubleValue());
        double dLng = Math.toRadians(xCoord.doubleValue() - previousXCoord.doubleValue());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(previousYCoord.doubleValue())) * Math.cos(Math.toRadians(yCoord.doubleValue())) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        long timeDiff = ChronoUnit.SECONDS.between(previousTime, LocalDateTime.now());
        Double speed = distance / timeDiff;

        previousXCoord = xCoord;
        previousYCoord = yCoord;
        previousTime = LocalDateTime.now();

        return BigDecimal.valueOf(speed).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private String calculateEventCode(String presttId, String nextsttId, BigDecimal xCoord, BigDecimal yCoord) {
        M_TP_NODE prevNode = nodeRepository.findById(Integer.valueOf(presttId)).orElse(null);
        M_TP_NODE nextNode = nodeRepository.findById(Integer.valueOf(nextsttId)).orElse(null);

        if (prevNode != null && nextNode != null) {
            double distanceToPrev = calculateDistance(xCoord, yCoord, prevNode.getXcord(), prevNode.getYcord());
            double distanceToNext = calculateDistance(xCoord, yCoord, nextNode.getXcord(), nextNode.getYcord());

            if (distanceToPrev <= 10) {
                return "02"; // 진입
            } else if (distanceToNext > 10) {
                return "03"; // 진출
            } else {
                return "01"; // 정주기
            }
        }
        return "01"; // 기본값: 정주기
    }

    private double calculateDistance(BigDecimal xCoord, BigDecimal yCoord, BigDecimal nodeX, BigDecimal nodeY) {
        double earthRadius = 6371000;
        double dLat = Math.toRadians(nodeY.doubleValue() - yCoord.doubleValue());
        double dLng = Math.toRadians(nodeX.doubleValue() - xCoord.doubleValue());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(yCoord.doubleValue())) * Math.cos(Math.toRadians(nodeY.doubleValue())) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }

    private void sendDataToCollectionServer(BusDataDTO busData) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(COLLECTION_SERVER_URL, busData, String.class);
        System.out.println("서버 응답: " + response);
    }
}
