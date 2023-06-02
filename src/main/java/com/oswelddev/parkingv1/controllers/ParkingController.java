package com.oswelddev.parkingv1.controllers;

import com.oswelddev.parkingv1.models.entity.Parking;
import com.oswelddev.parkingv1.services.ParkingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parking")
public class ParkingController {

    private final ParkingService parkingService;


    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    ResponseEntity<Page<Parking>> getParkingPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "true") boolean paid,
            @RequestParam(required = false, name = "licenseplate") String licencePlate){

        if (!paid){
            return new ResponseEntity<>(parkingService.getParkingPageByNotPaid(PageRequest.of(page,size)), HttpStatus.OK);
        }else if(licencePlate != null){
            return new ResponseEntity<>(parkingService.getParkingPageByLicencePlate(licencePlate,PageRequest.of(page,size)), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(parkingService.getParkingPage(PageRequest.of(page,size)), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    ResponseEntity<Parking> getParkingById(@PathVariable Long id){
        return new ResponseEntity<>(parkingService.getParkingById(id),HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    ResponseEntity<Parking> saveParking(@RequestBody Parking parking){
        return new ResponseEntity<>(parkingService.createParking(parking),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    ResponseEntity<Parking> updateParking(@PathVariable Long id,
                                          @RequestParam(defaultValue = "update") String status,
                                          @RequestParam(required = false, defaultValue = "true") boolean pay,
                                          @RequestBody Parking parking){

        if(status.equals("calculate")){
            return new ResponseEntity<>(parkingService.calculateParking(id),HttpStatus.OK);
        } else if (status.equals("pay")) {
            return new ResponseEntity<>(parkingService.parkingPay(id,pay),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(parkingService.updateParking(id,parking),HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Parking> deleteParking(@PathVariable long id){
        return new ResponseEntity<>(parkingService.deleteParkingById(id),HttpStatus.OK);
    }



}
