package com.oswelddev.parkingv1.models.repository;

import com.oswelddev.parkingv1.models.entity.ParkingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingDetailsRepository extends JpaRepository<ParkingDetails, Long> {
}