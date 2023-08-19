package com.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParkingGarageDTO {

    private Integer id;
    @NotBlank
    private String address;
    @NotNull
    @Min(value = 1)
    private Integer numOfParkingSpace;
}
