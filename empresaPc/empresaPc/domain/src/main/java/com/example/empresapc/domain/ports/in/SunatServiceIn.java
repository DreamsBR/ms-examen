package com.example.empresapc.domain.ports.in;

import com.example.empresapc.domain.aggregates.response.ResponseSunat;

public interface SunatServiceIn {

    ResponseSunat getInfoSunatIn(String numero);

}
