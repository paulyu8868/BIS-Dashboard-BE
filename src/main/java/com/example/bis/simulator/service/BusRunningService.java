package com.example.bis.simulator.service;

import com.example.bis.simulator.repository.BusRunningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BusRunningService {

    private final BusRunningRepository busRunningRepository;

    @Autowired
    public BusRunningService(BusRunningRepository busRunningRepository) {
        this.busRunningRepository = busRunningRepository;
    }

    @Transactional
    public boolean startBusOperation(String obuId) {
        try {
            int updated = busRunningRepository.startBusOperation(obuId);
            return updated > 0;
        } catch (Exception e) {
            throw new RuntimeException("운행 시작 처리 실패", e);
        }
    }

    @Transactional
    public boolean stopAllBusesOnRoute(String routeId) {
        try {
            int updated = busRunningRepository.stopAllBusesOnRoute(routeId);
            return updated > 0;
        } catch (Exception e) {
            throw new RuntimeException("노선 운행 종료 처리 실패", e);
        }
    }
}