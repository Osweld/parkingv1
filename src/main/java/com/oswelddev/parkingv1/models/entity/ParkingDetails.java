package com.oswelddev.parkingv1.models.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "parking_details")
@Getter
@Setter
@ToString
public class ParkingDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parking_details")
    private Long id;

    @Column(name = "start_time",nullable = false)
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;


    @Column(name = "rate",nullable = false)
    private BigDecimal rate;


    @Column(name = "time",nullable = false)
    private Integer time;


    @Column(name = "tolerance",nullable = false)
    private Integer tolerance;

    public ParkingDetails() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ParkingDetails that = (ParkingDetails) o;
        return id != null && Objects.equals(id, that.id);
    }


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    public Integer calculateTotalTime(){
        return Math.toIntExact((endTime.getTime() - startTime.getTime()) / (1000 * 60));
    }

    public BigDecimal calculateTotalAmount(Integer totalTime){
        int hours = totalTime / time;
        double fraction =  totalTime % time;
        double parkingRate = rate.doubleValue();
        if(fraction > tolerance){
            return BigDecimal.valueOf((hours + 1) * parkingRate);
        }else{
            return BigDecimal.valueOf((hours ) * parkingRate);
        }
    }
}
