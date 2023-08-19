package com.example.service;

import com.example.dto.CarDTO;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<CarDTO> findAll();
    Optional<CarDTO> findById(Integer id);
    CarDTO create(CarDTO carDTO);
    CarDTO update(CarDTO carDTO);
    void delete(Integer id);
}
