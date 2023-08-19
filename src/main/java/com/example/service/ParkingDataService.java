package com.example.service;

import com.example.dto.ParkingDataDTO;
import com.example.response.ParkingDataResponse;

import java.util.List;

public interface ParkingDataService {
    List<ParkingDataResponse> findAll();
    ParkingDataResponse create(ParkingDataDTO parkingDataDTO);

}
