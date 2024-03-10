package com.example.empresapc.domain.ports.out;

import com.example.empresapc.domain.aggregates.response.ResponseSunat;

public interface SunatServiceOut {

    ResponseSunat getInfoSunatOut(String numero);

}
