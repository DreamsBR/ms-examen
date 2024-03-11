package com.codigo.examen.service.impl;

import com.codigo.examen.entity.Rol;
import com.codigo.examen.entity.Usuario;
import com.codigo.examen.repository.RolRepository;
import com.codigo.examen.repository.UsuarioRepository;
import com.codigo.examen.request.SignInRequest;
import com.codigo.examen.request.SignUpRequest;
import com.codigo.examen.response.AuthenticationResponse;
import com.codigo.examen.service.AuthenticationService;
import com.codigo.examen.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Authenticator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthentificationServiceImpl  implements AuthenticationService {

    private final RolRepository rolRepository;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JWTService jwtService;


    @Override
    public Usuario signUpUser(SignUpRequest signUpRequest) {
            Usuario usuario = new Usuario();
            usuario.setUsername(signUpRequest.getUsername());
            usuario.setEmail(signUpRequest.getEmail());
            usuario.setTelefono(signUpRequest.getTelefono());

            Optional<Rol> userRolOptional = rolRepository.findByNombreRol("USER");
            if (!userRolOptional.isPresent()) {
                throw new RuntimeException("User Role not found.");
            }
            usuario.getRoles().add(userRolOptional.get());
            usuario.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
            return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario signUpAdmin(SignUpRequest signUpRequest) {
        Usuario usuario = new Usuario();
        usuario.setUsername(signUpRequest.getUsername());
        usuario.setEmail(signUpRequest.getEmail());
        usuario.setTelefono(signUpRequest.getTelefono());

        Optional<Rol> adminRolOptional = rolRepository.findByNombreRol("ADMIN");
        if (!adminRolOptional.isPresent()) {
            throw new RuntimeException("Admin Role not found.");
        }

        usuario.getRoles().add(adminRolOptional.get());
        usuario.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return usuarioRepository.save(usuario);
    }
    @Override
    public AuthenticationResponse signin(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(),signInRequest.getPassword()));
        var user = usuarioRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Useario no valido"));

        var jwt = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse =  new AuthenticationResponse();
        authenticationResponse.setToken(jwt);
        return authenticationResponse;
    }
}