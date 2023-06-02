package com.oswelddev.parkingv1.models.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cars")
@Getter
@Setter
@ToString
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car")
    private Long id;

    @NotEmpty
    @Size(max = 10)
    @Column(name = "license_plate")
    private String licensePlate;
    @Size(max = 300)
    @Column(name = "description")
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "brand")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "type")
    private CarType type;

    public Car() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return id != null && Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
