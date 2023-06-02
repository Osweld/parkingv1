package com.oswelddev.parkingv1.models.repository;

import com.oswelddev.parkingv1.models.entity.Parking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParkingRepository extends JpaRepository<Parking, Long> {


    Page<Parking> findAllByPaidFalse(Pageable pageable);

    @Query("SELECT p FROM Parking p WHERE p.car.licensePlate = ?1")
    Page<Parking> findAllByLicensePlate(String licencePlate,Pageable pageable);



}