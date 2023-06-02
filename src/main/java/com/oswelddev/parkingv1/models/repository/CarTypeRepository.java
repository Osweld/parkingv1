package com.oswelddev.parkingv1.models.repository;

import com.oswelddev.parkingv1.models.entity.CarType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarTypeRepository extends JpaRepository<CarType, Long> {
}