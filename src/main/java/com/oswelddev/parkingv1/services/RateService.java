package com.oswelddev.parkingv1.services;

import com.oswelddev.parkingv1.models.entity.Rate;

public interface RateService {

    Rate getRate();
    Rate updateRate(Rate updateRate);
}
