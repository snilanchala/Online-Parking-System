package com.example.response;

import com.example.dto.CarDTO;
import com.example.dto.ParkingGarageDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingDataResponse {

    @NotNull
    private Integer id;
    @NotBlank
    private ParkingGarageDTO parkingGarage;
    @NotBlank
    private CarDTO car;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
}
