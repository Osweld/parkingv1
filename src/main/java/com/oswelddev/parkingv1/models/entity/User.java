package com.oswelddev.parkingv1.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @NotEmpty
    @Size(min = 1,max = 45)
    @Column(name = "name",nullable = false)
    private String name;

    @NotEmpty
    @Size(min = 1,max = 45)
    @Column(name = "lastname",nullable = false)
    private String lastname;

    @NotEmpty
    @Size(min = 5,max = 45)
    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @NotNull
    @Column(name = "active",nullable = false)
    private Boolean active;

    @NotEmpty
    @Size(max = 60)
    @Column(name = "password",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToOne
    @JoinColumn(name="rol")
    private Rol rol;

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
