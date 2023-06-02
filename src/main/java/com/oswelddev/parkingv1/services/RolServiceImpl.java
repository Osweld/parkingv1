package com.oswelddev.parkingv1.services;


import com.oswelddev.parkingv1.models.entity.Rol;
import com.oswelddev.parkingv1.models.repository.RolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolServiceImpl implements RolService{

    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Rol getRolById(Long id) {
        return rolRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Rol saveRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    @Transactional
    public Rol updateRol(Rol updateRol, Long id) {
        return rolRepository.findById(id).map(rol -> {
            rol.setRol(updateRol.getRol());
            return rolRepository.save(rol);
        }).orElseThrow();
    }

    @Override
    @Transactional
    public Rol deleteRolById(Long id) {
        return rolRepository.findById(id).map( rol -> {
            rolRepository.deleteById(id);
            return rol;
        }).orElseThrow();
    }
}
