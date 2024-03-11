package com.codigo.examen;

import com.codigo.examen.entity.Rol;
import com.codigo.examen.repository.RolRepository;
import com.codigo.examen.service.impl.RolServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RolServiceTest {

    @InjectMocks
    private RolServiceImpl rolService;

    @Mock
    private RolRepository rolRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void FindRolByNombreRol() {
        Rol rol = new Rol();
        rol.setNombreRol("Admin");
        when(rolRepository.findByNombreRol(anyString())).thenReturn(Optional.of(rol));
        Optional<Rol> result = rolService.findByNombreRol("Admin");

        assertEquals(rol, result.get());
    }

    @Test
    void NotFindRolByNombreRol() {
        when(rolRepository.findByNombreRol(anyString())).thenReturn(Optional.empty());

        Optional<Rol> result = rolService.findByNombreRol("Admin");

        assertEquals(Optional.empty(), result);
    }
}
