package com.example.bis.simulator.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor  // 기본 생성자 자동 생성
@Entity
@Table(name = "bus_data")  // 테이블 이름을 적절히 설정
public class BusData {

    @Id
    private Long id;  // 기본 키 필드 추가

    @Column(name = "col_date")
    private LocalDateTime colDate;  // 수집 일시

    @Column(name = "bus_id")
    private String busId;            // 버스 ID

    @Column(name = "prett_id")
    private String prettId;          // 이전 정류장 ID

    @Column(name = "nextstt_id")
    private String nextsttId;       // 다음 정류장 ID

    @Column(name = "speed")
    private double speed;            // 속도

    @Column(name = "azimuth")
    private double azimuth;          // 방위각

    @Column(name = "longitude")
    private double longitude;        // 경도

    @Column(name = "latitude")
    private double latitude;         // 위도

    @Column(name = "event_code")
    private String eventCode;        // 이벤트 코드

    // ObjectMapper를 정적 필드로 선언
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // 매개변수를 가진 생성자 추가
    public BusData(LocalDateTime colDate, String busId, String prettId, String nextsttId,
                   double speed, double azimuth, double longitude, double latitude, String eventCode) {
        this.colDate = colDate;
        this.busId = busId;
        this.prettId = prettId;
        this.nextsttId = nextsttId;
        this.speed = speed;
        this.azimuth = azimuth;
        this.longitude = longitude;
        this.latitude = latitude;
        this.eventCode = eventCode;
    }

    // JSON 변환 메서드
    public String toJson() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            // 예외 발생 시 에러 로그 출력
            System.err.println("JSON 변환 에러: " + e.getMessage());
            return "{}";  // 에러 발생 시 빈 JSON 객체 반환
        }
    }
}
