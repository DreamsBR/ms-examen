package com.example.empresapc.infraestructure.rest;

import com.example.empresapc.domain.aggregates.response.ResponseSunat;
import com.example.empresapc.domain.ports.in.SunatServiceIn;
import com.example.empresapc.domain.ports.out.SunatServiceOut;
import com.example.empresapc.infraestructure.rest.client.EmpresaSunat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SunatRest implements SunatServiceOut {

    private final EmpresaSunat service;

    @Value("${token_api}")
    private String api_token;

    @Override
    public ResponseSunat getInfoSunatOut(String numero) {
        String token = "Bearer " + api_token;
        ResponseSunat rest =service.getInfoSunat(numero, token);
        return rest;
    }

}
