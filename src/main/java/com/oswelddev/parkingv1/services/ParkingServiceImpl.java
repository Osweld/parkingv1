package com.oswelddev.parkingv1.services;

import com.oswelddev.parkingv1.models.entity.Parking;
import com.oswelddev.parkingv1.models.entity.ParkingDetails;
import com.oswelddev.parkingv1.models.entity.Rate;
import com.oswelddev.parkingv1.models.repository.ParkingDetailsRepository;
import com.oswelddev.parkingv1.models.repository.ParkingRepository;
import com.oswelddev.parkingv1.models.repository.RateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ParkingServiceImpl implements ParkingService{

    // se creara el parking, se calculara el parking, se pagara el parking
    // se podra editar el parking, eliminar pagado del parking

    private final ParkingRepository parkingRepository;
    private final ParkingDetailsRepository parkingDetailsRepository;
    private final RateRepository rateRepository;

    public ParkingServiceImpl(ParkingRepository parkingRepository, ParkingDetailsRepository parkingDetailsRepository, RateRepository rateRepository) {
        this.parkingRepository = parkingRepository;
        this.parkingDetailsRepository = parkingDetailsRepository;
        this.rateRepository = rateRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Parking> getParkingPage(Pageable pageable) {
        return parkingRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Parking getParkingById(Long id) {
        return parkingRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Parking> getParkingPageByNotPaid(Pageable pageable) {
        return parkingRepository.findAllByPaidFalse(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Parking> getParkingPageByLicencePlate(String licencePlate,Pageable pageable) {
        return parkingRepository.findAllByLicensePlate(licencePlate,pageable);
    }

    @Override
    @Transactional()//En teoria ya lleva lo del auto, usuario solo falta modificarle los detalles
    public Parking createParking(Parking parking) {
        ParkingDetails parkingDetails = parking.getParkingDetails();
        Rate rate = rateRepository.findById(1L).orElseThrow();
        parking.setPaid(false);
        parkingDetails.setRate(rate.getRate());
        parkingDetails.setTolerance(rate.getTolerance());
        parkingDetails.setTime(rate.getTime());
        parkingDetails.setStartTime(new Date());
        parking.setParkingDetails(parkingDetailsRepository.save(parkingDetails));
        return parkingRepository.save(parking);
    }
    //Modificarlo
    @Override
    @Transactional()// no modificamos el usuario
    public Parking updateParking(Long id, Parking updateParking) {
        return parkingRepository.findById(id).map( parking -> {
            parking.setPaid(updateParking.getPaid());
            parking.setCar(updateParking.getCar());
           return calculateTotalAmountAndHours(parking,updateParking.getParkingDetails());
        }).orElseThrow();
    }

    @Override
    @Transactional()
    public Parking calculateParking(Long id) {
        return parkingRepository.findById(id).map(
                parking -> {
                    ParkingDetails parkingDetails = parking.getParkingDetails();
                    parkingDetails.setEndTime(new Date());
                    return calculateTotalAmountAndHours(parking,parkingDetails);
                }).orElseThrow();
    }


    @Override
    @Transactional()
    public Parking deleteParkingById(Long id) {
        return parkingRepository.findById(id).map(parking -> {
            parkingRepository.deleteById(id);
            return parking;
        }).orElseThrow();
    }
    @Override
    @Transactional()//en teoria ya tiene lleno la fecha de inicio y fin solo calcular
    public Parking parkingPay(Long id,Boolean isPay) {
        return parkingRepository.findById(id).map( parking ->{
            parking.setPaid(isPay);
            return parkingRepository.save(parking);
        }).orElseThrow();
    }


    private Parking calculateTotalAmountAndHours(Parking parking, ParkingDetails parkingDetails){
        parking.setParkingDetails(parkingDetails);
        parking.setTotalTime(parkingDetails.calculateTotalTime());
        parking.setAmount(parkingDetails.calculateTotalAmount(parking.getTotalTime()));
        return parkingRepository.save(parking);
    }
}
