package com.oswelddev.parkingv1.services;

import com.oswelddev.parkingv1.models.entity.CarType;
import com.oswelddev.parkingv1.models.repository.CarTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarTypeServiceImpl implements CarTypeService{

    private final CarTypeRepository carTypeRepository;

    public CarTypeServiceImpl(CarTypeRepository carTypeRepository) {
        this.carTypeRepository = carTypeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarType> getAllCarTypes() {
        return carTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CarType getCarTypeById(Long id) {
        return carTypeRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public CarType saveCarType(CarType carType) {
        return carTypeRepository.save(carType);
    }

    @Override
    @Transactional
    public CarType updateCarType(CarType updateCarType, Long id) {
        return carTypeRepository.findById(id).map( carType -> {
            carType.setType(updateCarType.getType().toLowerCase());
            return carTypeRepository.save(carType);
        }).orElseThrow();
    }

    @Override
    @Transactional
    public CarType deleteCarTypeById(Long id) {
        return carTypeRepository.findById(id).map(carType -> {
            carTypeRepository.deleteById(id);
            return carType;
        }).orElseThrow();
    }
}
