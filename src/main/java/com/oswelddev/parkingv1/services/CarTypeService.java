package com.oswelddev.parkingv1.services;

import com.oswelddev.parkingv1.models.entity.CarType;

import java.util.List;

public interface CarTypeService {

    List<CarType> getAllCarTypes();
    CarType getCarTypeById(Long id);
    CarType saveCarType(CarType carType);
    CarType updateCarType(CarType updateCarType,Long id);
    CarType deleteCarTypeById(Long id);

}
