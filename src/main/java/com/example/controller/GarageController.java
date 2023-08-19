package com.example.controller;

import com.example.dto.ParkingGarageDTO;
import com.example.exception.InvalidRequestException;
import com.example.service.GarageService;
import com.example.util.RequestUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/garage")
public class GarageController {

    @Autowired
    private GarageService garageService;
    @Autowired
    private RequestUtil requestUtil;

    @GetMapping("/getAll")
    public ResponseEntity<List<ParkingGarageDTO>> findAll() {
        List<ParkingGarageDTO> garages = garageService.findAll();
        return ResponseEntity.ok().body(garages);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ParkingGarageDTO> findById(@PathVariable Integer id) {
        Optional<ParkingGarageDTO> garage = garageService.findById(id);
        return garage.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<ParkingGarageDTO> create(@Valid @RequestBody ParkingGarageDTO parkingGarageDTO,
            BindingResult bindingResult)
            throws InvalidRequestException {
        requestUtil.checkForRequestErrors(bindingResult);

        ParkingGarageDTO savedGarage = garageService.create(parkingGarageDTO);
        ResponseEntity<ParkingGarageDTO> body = ResponseEntity.status(HttpStatus.CREATED)
                .body(savedGarage);
        return body;
    }

    @PutMapping("/update")
    public ResponseEntity<ParkingGarageDTO> update(@Valid @RequestBody ParkingGarageDTO parkingGarageDTO,
            BindingResult bindingResult)
            throws InvalidRequestException {

        if (Objects.isNull(parkingGarageDTO.getId())) {
            bindingResult.rejectValue("id","","must not be null");
        }
        requestUtil.checkForRequestErrors(bindingResult);

        ParkingGarageDTO updatedGarage = garageService.update(parkingGarageDTO);
        return ResponseEntity.ok()
                .body(updatedGarage);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        garageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
