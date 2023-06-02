package com.oswelddev.parkingv1.controllers;

import com.oswelddev.parkingv1.models.entity.Car;
import com.oswelddev.parkingv1.services.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {


    private final CarService carService;


    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    ResponseEntity<Page<Car>> getCarPage(@RequestParam(value = "0") int page,@RequestParam(value = "10") int size){
        return new ResponseEntity<>(carService.getCarPage(PageRequest.of(page,size)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    ResponseEntity<Car> getCar(@PathVariable Long id){
        return new ResponseEntity<>(carService.getCarById(id),HttpStatus.OK);
    }

    @GetMapping("license/{license}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    ResponseEntity<Car> getCarByLicensePlate(@PathVariable String license){
        return new ResponseEntity<>(carService.getCarByLicencePlate(license),HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    ResponseEntity<Car> saveCar(@RequestBody Car car){
        return new ResponseEntity<>(carService.saveCar(car),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    ResponseEntity<Car> updateCar(@RequestBody Car car,@PathVariable Long id){
        return new ResponseEntity<>(carService.updateCar(car,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Car> deleteCar(@PathVariable Long id){
        return new ResponseEntity<>(carService.deleteCarById(id),HttpStatus.OK);
    }
}
