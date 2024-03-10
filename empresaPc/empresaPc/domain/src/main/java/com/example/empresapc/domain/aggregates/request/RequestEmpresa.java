package com.example.empresapc.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEmpresa {
    private String numDoc;
    private String tipoEmp;
    private String tipoDoc;
}
