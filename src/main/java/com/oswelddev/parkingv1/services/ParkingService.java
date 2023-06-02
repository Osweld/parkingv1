package com.oswelddev.parkingv1.services;

import com.oswelddev.parkingv1.models.entity.Parking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParkingService {

    Page<Parking> getParkingPage(Pageable pageable);
    Parking getParkingById(Long id);
    Page<Parking> getParkingPageByNotPaid(Pageable pageable);
    Page<Parking> getParkingPageByLicencePlate(String licencePlate,Pageable pageable);
    Parking createParking(Parking parking);
    Parking updateParking(Long id,Parking updateParking);
    Parking calculateParking(Long id);
    Parking deleteParkingById(Long id);
    Parking parkingPay(Long id, Boolean isPay);


}
