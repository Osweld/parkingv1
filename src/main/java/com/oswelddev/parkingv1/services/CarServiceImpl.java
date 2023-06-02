package com.oswelddev.parkingv1.services;

import com.oswelddev.parkingv1.models.entity.Car;
import com.oswelddev.parkingv1.models.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Car> getCarPage(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Car getCarByLicencePlate(String licensePlate) {
        return carRepository.findByLicensePlate(licensePlate).orElseThrow();
    }

    @Override
    @Transactional
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    @Transactional
    public Car updateCar(Car updateCar, Long id) {
        return carRepository.findById(id).map( car -> {
            car.setDescripcion(updateCar.getDescripcion());
            car.setLicensePlate(updateCar.getLicensePlate());
            car.setBrand(updateCar.getBrand());
            car.setType(updateCar.getType());
            return carRepository.save(car);
        }).orElseThrow();
    }

    @Override
    @Transactional
    public Car deleteCarById(Long id) {
        return carRepository.findById(id).map( car -> {
            carRepository.deleteById(id);
            return car;
        }).orElseThrow();
    }
}
