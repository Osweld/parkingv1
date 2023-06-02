package com.oswelddev.parkingv1.models.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "parkings")
@Getter
@Setter
@ToString
public class Parking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parking")
    private Long id;

    @Column(name = "total_time", nullable = false)
    private Integer totalTime;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "paid", nullable = false)
    private Boolean paid;

    @Column(name = "create_at", nullable = false)
    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "car")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "parking_details")
    private ParkingDetails parkingDetails;


    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    public Parking() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Parking parking = (Parking) o;
        return id != null && Objects.equals(id, parking.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
