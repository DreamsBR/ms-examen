package com.example.empresapc.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpresaDTO {
    private Long id;
    private String numDoc;
    private String tipoEmp;
    private String tipoDoc;
    private String razonSocial;
    private String nomComercial;
    private Integer estado;
}
