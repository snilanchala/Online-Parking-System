package com.example.repository;

import com.example.entity.ParkingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingDataRepository extends JpaRepository<ParkingData, Integer> {
}
