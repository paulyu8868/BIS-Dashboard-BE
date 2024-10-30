package com.example.bis.simulator.repository;

import com.example.bis.simulator.model.BusData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BusDataRepository extends JpaRepository<BusData, Long> {
    List<BusData> findByBusId(String busId);
    Optional<BusData> findTopByBusIdOrderByColDateDesc(String busId);
}
