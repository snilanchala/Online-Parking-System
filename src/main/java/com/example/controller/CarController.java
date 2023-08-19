package com.example.controller;

import com.example.dto.CarDTO;
import com.example.exception.CarNotFoundException;
import com.example.exception.DatabaseConstraintViolationException;
import com.example.exception.InvalidRequestException;
import com.example.service.CarService;
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
@RequestMapping("/vehicle")
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private RequestUtil requestUtil;

    @GetMapping("/getAll")
    public ResponseEntity<List<CarDTO>> findAll() {
        List<CarDTO> cars = carService.findAll();
        return ResponseEntity.ok().body(cars);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CarDTO> findById(@PathVariable Integer id) {
        Optional<CarDTO> car = carService.findById(id);
        return car.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<CarDTO> create(@Valid @RequestBody CarDTO carDTO, BindingResult bindingResult)
            throws InvalidRequestException, DatabaseConstraintViolationException {
        requestUtil.checkForRequestErrors(bindingResult);

        CarDTO savedCar = carService.create(carDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCar);
    }

    @PutMapping("/update")
    public ResponseEntity<CarDTO> update(@Valid @RequestBody CarDTO carDTO, BindingResult bindingResult)
            throws InvalidRequestException, CarNotFoundException, DatabaseConstraintViolationException {

        if (Objects.isNull(carDTO.getId())) {
            bindingResult.rejectValue("id","","must not be null");
        }
        requestUtil.checkForRequestErrors(bindingResult);

        CarDTO updatedCar = carService.update(carDTO);
        return ResponseEntity.ok().body(updatedCar);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id)
            throws CarNotFoundException {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}