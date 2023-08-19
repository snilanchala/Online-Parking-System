package com.example.service;

import com.example.dto.CarDTO;
import com.example.dto.ParkingDataDTO;
import com.example.dto.ParkingGarageDTO;
import com.example.entity.Car;
import com.example.entity.ParkingData;
import com.example.entity.ParkingGarage;
import com.example.exception.CarNotFoundException;
import com.example.exception.ParkingGarageNotFoundException;
import com.example.repository.CarRepository;
import com.example.repository.ParkingDataRepository;
import com.example.repository.ParkingGarageRepository;
import com.example.response.ParkingDataResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingDataServiceImpl implements ParkingDataService{
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ParkingDataRepository parkingRepository;
    @Autowired
    private ParkingGarageRepository garageRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<ParkingDataResponse> findAll() {

        List<ParkingDataDTO> allParking = new ArrayList<>();
        return parkingRepository.findAll()
                .stream()
                .map(parking -> modelMapper.map(parking, ParkingDataResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ParkingDataResponse create(ParkingDataDTO parkingDataDTO) {
        String parkingGarageAddress = parkingDataDTO.getParkingGarageAddress();
        ParkingGarage parkingGarage = garageRepository.findByAddress(parkingGarageAddress)
                .orElseThrow(() -> new ParkingGarageNotFoundException(
                        String.format("Parking garage not found with address=%s", parkingGarageAddress)));

        String carLicensePlate = parkingDataDTO.getCarLicensePlate();
        Car car = carRepository.findByLicenseNo(carLicensePlate)
                .orElseThrow(() -> new CarNotFoundException(
                        String.format("Car not found with license plate=%s", carLicensePlate)));

        ParkingData saveData = parkingRepository.save(mapParkingDataDTOToParkingData(parkingDataDTO, parkingGarage, car));
        return mapParkingDataDTOToParkingDataResponse(saveData);
    }

    private ParkingDataResponse mapParkingDataDTOToParkingDataResponse(ParkingData saveParking) {

        ParkingGarage parkingGarage = saveParking.getParkingGarage();
        Car car = saveParking.getCar();

        saveParking.setParkingGarage(null);
        saveParking.setCar(null);

        ParkingDataResponse resultParking = modelMapper.map(saveParking, ParkingDataResponse.class);
        resultParking.setParkingGarage(modelMapper.map(parkingGarage, ParkingGarageDTO.class));
        resultParking.setCar(modelMapper.map(car, CarDTO.class));

        return resultParking;
    }

    private ParkingData mapParkingDataDTOToParkingData(ParkingDataDTO parkingDataDTO,
                                                  ParkingGarage parkingGarage,
                                                  Car car) {

        parkingDataDTO.setId(null);
        parkingDataDTO.setParkingGarageAddress(null);
        parkingDataDTO.setCarLicensePlate(null);

        ParkingData parkingData = modelMapper.map(parkingDataDTO, ParkingData.class);
        parkingData.setParkingGarage(parkingGarage);
        parkingData.setCar(car);

        return parkingData;
    }
}
