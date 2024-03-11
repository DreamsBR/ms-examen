package com.codigo.examen.service.impl;

import com.codigo.examen.entity.Rol;
import com.codigo.examen.repository.RolRepository;
import com.codigo.examen.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;


    @Override
    public Optional<Rol> findByNombreRol(String nombreRol) {
        return rolRepository.findByNombreRol(nombreRol);
    }
}
