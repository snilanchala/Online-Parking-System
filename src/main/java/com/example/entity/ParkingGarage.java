package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ParkingGarage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    @NotBlank
    private String address;
    @Column(nullable = false)
    @NotNull
    @Min(value = 1)
    private Integer numOfParkingSpace;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parkingGarage")
    private List<ParkingData> garageParkingData;
}
