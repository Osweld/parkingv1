package com.oswelddev.parkingv1.models.repository;

import com.oswelddev.parkingv1.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);

}