package com.oswelddev.parkingv1.services;

import com.oswelddev.parkingv1.models.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {

    Page<Car> getCarPage(Pageable pageable);
    Car getCarById(Long id);
    Car getCarByLicencePlate(String licensePlate);
    Car saveCar(Car car);
    Car updateCar(Car updateCar, Long id);
    Car deleteCarById(Long id);


}
