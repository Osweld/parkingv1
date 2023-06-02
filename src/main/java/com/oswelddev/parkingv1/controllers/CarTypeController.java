package com.oswelddev.parkingv1.controllers;

import com.oswelddev.parkingv1.models.entity.CarType;
import com.oswelddev.parkingv1.services.CarTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cartypes")
public class CarTypeController {

    private final CarTypeService carTypeService;

    public CarTypeController(CarTypeService carTypeService) {
        this.carTypeService = carTypeService;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    ResponseEntity<List<CarType>> getAllCarTypes(){
        return new ResponseEntity<>(carTypeService.getAllCarTypes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    ResponseEntity<CarType> getCarType(@PathVariable Long id){
        return new ResponseEntity<>(carTypeService.getCarTypeById(id), HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<CarType> saveCarType(@RequestBody CarType carType){
        return new ResponseEntity<>(carTypeService.saveCarType(carType), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<CarType> updateCarType(@RequestBody CarType carType,@PathVariable Long id){
        return new ResponseEntity<>(carTypeService.updateCarType(carType,id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<CarType> deleteCarType(@PathVariable Long id){
        return new ResponseEntity<>(carTypeService.deleteCarTypeById(id), HttpStatus.OK);
    }
}
