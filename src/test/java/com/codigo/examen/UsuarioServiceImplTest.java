package com.codigo.examen;

import com.codigo.examen.entity.Rol;
import com.codigo.examen.entity.Usuario;
import com.codigo.examen.repository.UsuarioRepository;
import com.codigo.examen.repository.RolRepository;
import com.codigo.examen.service.impl.UsuarioServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RolRepository rolRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void CreateUsuarioWhenNonExistingUsername() {
        Usuario usuario = new Usuario();
        usuario.setUsername("newUser");
        when(usuarioRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<Usuario> result = usuarioService.createUsuario(usuario);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(usuario, result.getBody());
    }

    @Test
    void NotCreateUsuarioWhenExistingUsername() {
        Usuario usuario = new Usuario();
        usuario.setUsername("existingUser");
        when(usuarioRepository.findByUsername(anyString())).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuario> result = usuarioService.createUsuario(usuario);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void GetUsuarioByIdWhenUserExists() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuario> result = usuarioService.getUsuarioById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(usuario, result.getBody());
    }

    @Test
    void NotGetUsuarioByIdWhenUserDoesNotExist() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Usuario> result = usuarioService.getUsuarioById(1L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void UpdateUsuarioWhenUserExistsAndUsernameIsUnique() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setUsername("newUsername");
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        when(usuarioRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<Usuario> result = usuarioService.updateUsuario(1L, usuario);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(usuario, result.getBody());
    }

    @Test
    void NotUpdateUsuarioWhenUserDoesNotExist() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Usuario> result = usuarioService.updateUsuario(1L, usuario);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void DeleteUsuarioWhenUserExists() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuario> result = usuarioService.deleteUsuario(1L);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void NotDeleteUsuarioWhenUserDoesNotExist() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Usuario> result = usuarioService.deleteUsuario(1L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void NotUpdateUsuarioWhenUserExistsAndUsernameIsNotUnique() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setUsername("existingUsername");
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        when(usuarioRepository.findByUsername(anyString())).thenReturn(Optional.of(new Usuario()));

        ResponseEntity<Usuario> result = usuarioService.updateUsuario(1L, usuario);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}