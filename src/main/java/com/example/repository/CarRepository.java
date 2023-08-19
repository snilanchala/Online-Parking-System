package com.example.repository;

import com.example.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByLicenseNo(String carLicensePlate);
}
