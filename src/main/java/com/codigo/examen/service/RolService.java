package com.codigo.examen.service;

import com.codigo.examen.entity.Rol;

import java.util.Optional;

public interface RolService {
    Optional<Rol> findByNombreRol(String nombreRol);

}
