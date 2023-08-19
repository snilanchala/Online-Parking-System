package com.example.service;

import com.example.dto.ParkingGarageDTO;
import com.example.entity.ParkingGarage;
import com.example.exception.DatabaseConstraintViolationException;
import com.example.exception.ParkingGarageNotFoundException;
import com.example.repository.ParkingGarageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GarageServiceImpl implements GarageService{
    @Autowired
    private ParkingGarageRepository garageRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<ParkingGarageDTO> findAll() {
        return garageRepository.findAll()
                .stream()
                .map(garage -> modelMapper.map(garage, ParkingGarageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ParkingGarageDTO> findById(Integer id) {
        return garageRepository.findById(id)
                .map(garage -> modelMapper.map(garage, ParkingGarageDTO.class));
    }

    @Override
    public ParkingGarageDTO create(ParkingGarageDTO parkingGarageDTO) {
        parkingGarageDTO.setId(null);
        ParkingGarage mapped = modelMapper.map(parkingGarageDTO, ParkingGarage.class);
        ParkingGarage savedGarage;
        try{
            savedGarage = garageRepository.save(mapped);
        }catch (Exception e){
            throw new DatabaseConstraintViolationException(e.getMessage());
        }
        return modelMapper.map(savedGarage, ParkingGarageDTO.class);
    }

    @Override
    public ParkingGarageDTO update(ParkingGarageDTO parkingGarageDTO) {
        Integer id = parkingGarageDTO.getId();
        garageRepository.findById(id)
                .orElseThrow(()-> new ParkingGarageNotFoundException(
                        String.format("Garage not found with id=%d", id)));

        ParkingGarage mapped = modelMapper.map(parkingGarageDTO, ParkingGarage.class);
        ParkingGarage savedGarage;
        try{
            savedGarage = garageRepository.save(mapped);
        }catch (Exception e){
            throw new DatabaseConstraintViolationException(e.getMessage());
        }
        return modelMapper.map(savedGarage, ParkingGarageDTO.class);
    }

    @Override
    public void delete(Integer id) {
        garageRepository.deleteById(id);
    }
}
