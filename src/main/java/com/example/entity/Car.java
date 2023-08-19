package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[A-Za-z]{1,3}-[A-Za-z]{1,2}-[0-9]{1,4}$")
    @NotBlank
    private String licenseNo;
    @Column(nullable = false)
    @NotBlank
    private String color;
    @Column(nullable = false)
    @NotBlank
    private String type;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car")
    private List<ParkingData> carParkingData;
}
