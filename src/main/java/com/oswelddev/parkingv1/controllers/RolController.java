package com.oswelddev.parkingv1.controllers;

import com.oswelddev.parkingv1.models.entity.Rol;
import com.oswelddev.parkingv1.services.RolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RolController {

    private final RolService rolService;


    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    ResponseEntity<List<Rol>> getAllRoles(){
        return new ResponseEntity<>(rolService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    ResponseEntity<Rol> getRol(@PathVariable Long id){
        return new ResponseEntity<>(rolService.getRolById(id),HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Rol> saveRol(@RequestBody Rol rol){
        return new ResponseEntity<>(rolService.saveRol(rol),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Rol> updateRol(@RequestBody Rol rol, @PathVariable Long id){
        return new ResponseEntity<>(rolService.updateRol(rol,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Rol> deleteRol(@PathVariable Long id){
        return new ResponseEntity<>(rolService.deleteRolById(id),HttpStatus.OK);
    }
}
