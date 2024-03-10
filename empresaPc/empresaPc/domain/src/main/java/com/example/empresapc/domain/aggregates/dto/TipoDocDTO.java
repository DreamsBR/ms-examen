package com.example.empresapc.domain.aggregates.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocDTO {

    private Long idTipoDocumento;
    private String codTipo;
    private String descTipo;
    private int estado;
    private String usuaCrea;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;

}
