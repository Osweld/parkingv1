package com.oswelddev.parkingv1.models.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_roles")
    private Long id;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "rol",nullable = false,unique = true)
    private String rol;

    public Rol() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rol rol = (Rol) o;
        return id != null && Objects.equals(id, rol.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
