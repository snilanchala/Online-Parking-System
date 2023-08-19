package com.example.controller;

import com.example.dto.ParkingDataDTO;
import com.example.exception.InvalidRequestException;
import com.example.response.ParkingDataResponse;
import com.example.service.ParkingDataService;
import com.example.util.RequestUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class ParkingController {

    @Autowired
    private ParkingDataService parkingService;
    @Autowired
    private RequestUtil requestUtil;

    @GetMapping
    public ResponseEntity<List<ParkingDataResponse>> findAll() {
        List<ParkingDataResponse> parkingList = parkingService.findAll();
        return ResponseEntity.ok().body(parkingList);
    }

    @PostMapping("/add")
    public ResponseEntity<ParkingDataResponse> create(@Valid @RequestBody ParkingDataDTO parkingDataDTO,
                                                      BindingResult bindingResult)
                                                        throws InvalidRequestException {
        requestUtil.checkForRequestErrors(bindingResult);

        ParkingDataResponse savedParking = parkingService.create(parkingDataDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedParking);
    }
}
