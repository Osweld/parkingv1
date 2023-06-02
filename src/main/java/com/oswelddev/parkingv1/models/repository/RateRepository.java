package com.oswelddev.parkingv1.models.repository;

import com.oswelddev.parkingv1.models.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {
}