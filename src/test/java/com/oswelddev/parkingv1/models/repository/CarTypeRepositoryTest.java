package com.oswelddev.parkingv1.models.repository;

import com.oswelddev.parkingv1.models.entity.CarType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CarTypeRepositoryTest {

    @Autowired
    private CarTypeRepository carTypeRepository;


    @Test
    void testFindById() {
        Optional<CarType> carType = carTypeRepository.findById(1L);
        assertEquals("sedan",carType.orElseThrow().getType());
    }

//    @Test
//    void testFindByIdThrowException() {
//        Optional<CarType> carType = carTypeRepository.findById(100L);
//        assertThrows(NoSuchElementException.class,() ->{
//            carType.orElseThrow();
//        });
//    }

    @Test
    void testFindByIdThrowException() {
        Optional<CarType> carType = carTypeRepository.findById(100L);
        assertThrows(NoSuchElementException.class, carType::orElseThrow);
    }


}