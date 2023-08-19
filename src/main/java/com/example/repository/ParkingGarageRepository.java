package com.example.repository;

import com.example.entity.ParkingGarage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingGarageRepository extends JpaRepository<ParkingGarage, Integer> {
    Optional<ParkingGarage> findByAddress(String address);
}
