package com.example.bis.simulator.service;

import com.example.bis.simulator.model.BusData;
import com.example.bis.simulator.repository.BusDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BusSimulatorService {

    private final BusDataRepository busDataRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String serverUrl = "http://your-server-url/api/bus-data";
    private final Random random = new Random();
    private final List<String> busIds = List.of("BUS001", "BUS002", "BUS003", "BUS004", "BUS005");

    @Autowired
    public BusSimulatorService(BusDataRepository busDataRepository) {
        this.busDataRepository = busDataRepository;
    }

    @Scheduled(fixedRate = 5000) // Execute every 5 seconds
    public void sendBusData() {
        for (String busId : busIds) {
            Optional<BusData> latestData = busDataRepository.findTopByBusIdOrderByColDateDesc(busId);
            BusData busData = generateBusData(busId, latestData);
            busDataRepository.save(busData);
            String jsonData = busData.toJson();
            sendDataToServer(jsonData);
            System.out.println("Sent data: " + jsonData);
        }
    }

    private BusData generateBusData(String busId, Optional<BusData> latestData) {
        double longitude = latestData.map(BusData::getLongitude).orElse(random.nextDouble() * 180 - 90);
        double latitude = latestData.map(BusData::getLatitude).orElse(random.nextDouble() * 90 - 45);

        // Generate new position with slight variation
        double newLongitude = longitude + random.nextDouble() * 0.01 - 0.005;
        double newLatitude = latitude + random.nextDouble() * 0.01 - 0.005;

        double speed = calculateSpeed(latitude, longitude, newLatitude, newLongitude, latestData);
        double azimuth = 0.0; // Azimuth placeholder, set to 0 as per requirements
        String eventCode = determineEventCode(speed);

        return new BusData(LocalDateTime.now(), busId, "prettId", "nextsttId", speed, azimuth, newLongitude, newLatitude, eventCode);
    }

    private double calculateSpeed(double lat1, double lon1, double lat2, double lon2, Optional<BusData> latestData) {
        if (latestData.isPresent()) {
            final int R = 6371; // Earth's radius in km
            double latDistance = Math.toRadians(lat2 - lat1);
            double lonDistance = Math.toRadians(lon2 - lon1);
            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double distance = R * c; // Distance in km

            Duration timeElapsed = Duration.between(latestData.get().getColDate(), LocalDateTime.now());
            double timeElapsedHours = timeElapsed.toSeconds() / 3600.0;

            return distance / timeElapsedHours;
        } else {
            return random.nextDouble() * 80; // Initial random speed setting
        }
    }

    private String determineEventCode(double speed) {
        if (speed < 5) return "01"; // Idle
        else if (speed < 15) return "02"; // Approaching
        else return "03"; // Departing
    }

    private void sendDataToServer(String jsonData) {
        try {
            restTemplate.postForObject(serverUrl, jsonData, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
