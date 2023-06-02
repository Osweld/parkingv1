package com.oswelddev.parkingv1.models.repository;

import com.oswelddev.parkingv1.models.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {



    Optional<Car> findByLicensePlate(String licensePlate);
}