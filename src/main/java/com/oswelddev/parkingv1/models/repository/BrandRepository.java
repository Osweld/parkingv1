package com.oswelddev.parkingv1.models.repository;

import com.oswelddev.parkingv1.models.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}