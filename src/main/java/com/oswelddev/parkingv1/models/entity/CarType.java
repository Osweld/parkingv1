package com.oswelddev.parkingv1.models.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "car_types")
@Getter
@Setter
@ToString
public class CarType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car_type")
    private Long id;

    @NotEmpty
    @Size(max = 45)
    @Column(name = "type",nullable = false,unique = true)
    private String type;

    public CarType() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CarType carType = (CarType) o;
        return id != null && Objects.equals(id, carType.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
