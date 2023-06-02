package com.oswelddev.parkingv1.services;

import com.oswelddev.parkingv1.models.entity.Rate;
import com.oswelddev.parkingv1.models.repository.RateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RateServiceImpl implements RateService{

    private final RateRepository rateRepository;

    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Rate getRate() {
        return rateRepository.findById(1L).orElseThrow();
    }

    @Override
    @Transactional
    public Rate updateRate(Rate updateRate) {
        return rateRepository.findById(1L).map( rate -> {
            rate.setTime(updateRate.getTime());
            rate.setTolerance(updateRate.getTolerance());
            rate.setRate(updateRate.getRate());
            rateRepository.save(rate);
            return rate;
        }).orElseThrow();
    }
}
