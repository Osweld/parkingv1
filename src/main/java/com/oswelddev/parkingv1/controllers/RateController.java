package com.oswelddev.parkingv1.controllers;

import com.oswelddev.parkingv1.models.entity.Rate;
import com.oswelddev.parkingv1.services.RateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rates")
public class RateController {

    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Rate> getRate(){
        return new ResponseEntity<>(rateService.getRate(), HttpStatus.OK);
    }

    @PutMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Rate> updateRate(@RequestBody Rate rate){
        return new ResponseEntity<>(rateService.updateRate(rate), HttpStatus.OK);
    }
}
