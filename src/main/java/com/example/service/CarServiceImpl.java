package com.example.service;

import com.example.dto.CarDTO;
import com.example.entity.Car;
import com.example.exception.CarNotFoundException;
import com.example.exception.DatabaseConstraintViolationException;
import com.example.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<CarDTO> findAll() {
        return carRepository.findAll()
                .stream()
                .map(car -> modelMapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CarDTO> findById(Integer id) {
        return carRepository.findById(id)
                .map(car -> modelMapper.map(car, CarDTO.class));
    }

    @Override
    public CarDTO create(CarDTO carDTO) {
        carDTO.setId(null);
        Car toSave = modelMapper.map(carDTO, Car.class);
        Car savedCar;
        try{
            savedCar = carRepository.save(toSave);
        }catch(Exception e){
            throw new DatabaseConstraintViolationException(e.getMessage());
        }
        return modelMapper.map(savedCar, CarDTO.class);
    }

    @Override
    public CarDTO update(CarDTO carDTO) {
        Integer dtoId = carDTO.getId();
        carRepository.findById(dtoId)
                .orElseThrow(() -> new CarNotFoundException(String
                        .format("Car not found with id=%d", dtoId)));
        Car mapped = modelMapper.map(carDTO, Car.class);
        Car savedCar;
        try{
            savedCar = carRepository.save(mapped);
        }catch(Exception e){
            throw new DatabaseConstraintViolationException(e.getMessage());
        }
        return modelMapper.map(savedCar, CarDTO.class);
    }

    @Override
    public void delete(Integer id) {
        carRepository.deleteById(id);
    }
}
