package com.oswelddev.parkingv1.models.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "rates")
@Getter
@Setter
@ToString
public class Rate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rates")
    private Long id;

    @Column(name = "rate",nullable = false)
    private BigDecimal rate;

    //El tiempo va a estar en minutos
    @Column(name = "time",nullable = false)
    private Integer time;
    //La tolerancia va a estar en minutos
    @Column(name = "tolerance",nullable = false)
    private Integer tolerance;

    public Rate() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rate rate = (Rate) o;
        return id != null && Objects.equals(id, rate.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
