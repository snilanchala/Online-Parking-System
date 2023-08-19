package com.example.service;

import com.example.dto.ParkingGarageDTO;

import java.util.List;
import java.util.Optional;

public interface GarageService {
    List<ParkingGarageDTO> findAll();
    Optional<ParkingGarageDTO> findById(Integer id);
    ParkingGarageDTO create(ParkingGarageDTO parkingGarageDTO);
    ParkingGarageDTO update(ParkingGarageDTO parkingGarageDTO);
    void delete(Integer id);
}
