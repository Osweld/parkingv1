package com.oswelddev.parkingv1.models.repository;

import com.oswelddev.parkingv1.models.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
}