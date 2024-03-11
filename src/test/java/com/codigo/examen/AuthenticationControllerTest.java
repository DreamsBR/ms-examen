package com.codigo.examen;

import com.codigo.examen.controller.AuthenticationController;
import com.codigo.examen.entity.Usuario;
import com.codigo.examen.request.SignInRequest;
import com.codigo.examen.request.SignUpRequest;
import com.codigo.examen.response.AuthenticationResponse;
import com.codigo.examen.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class AuthenticationControllerTest {


    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void signUpUserReturnsUsuario() {
        SignUpRequest signUpRequest = new SignUpRequest();
        Usuario usuario = new Usuario();
        when(authenticationService.signUpUser(signUpRequest)).thenReturn(usuario);

        ResponseEntity<Usuario> response = authenticationController.signUpUser(signUpRequest);

        assertEquals(usuario, response.getBody());
    }

    @Test
    public void signUpAdminReturnsUsuario() {
        SignUpRequest signUpRequest = new SignUpRequest();
        Usuario usuario = new Usuario();
        when(authenticationService.signUpAdmin(signUpRequest)).thenReturn(usuario);

        ResponseEntity<Usuario> response = authenticationController.signUpAdmin(signUpRequest);

        assertEquals(usuario, response.getBody());
    }

    @Test
    public void signInReturnsAuthenticationResponse() {
        SignInRequest signInRequest = new SignInRequest();
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        when(authenticationService.signin(signInRequest)).thenReturn(authenticationResponse);

        ResponseEntity<AuthenticationResponse> response = authenticationController.signin(signInRequest);

        assertEquals(authenticationResponse, response.getBody());
    }
}
