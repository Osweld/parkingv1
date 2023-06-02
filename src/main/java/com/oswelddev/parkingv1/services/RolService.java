package com.oswelddev.parkingv1.services;

import com.oswelddev.parkingv1.models.entity.Rol;

import java.util.List;

public interface RolService {


    List<Rol> getAllRoles();
    Rol getRolById(Long id);
    Rol saveRol(Rol rol);
    Rol updateRol(Rol updateRol,Long id);
    Rol deleteRolById(Long id);
}
