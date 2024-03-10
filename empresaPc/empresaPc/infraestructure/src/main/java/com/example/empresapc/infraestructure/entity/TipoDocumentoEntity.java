package com.example.empresapc.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "tipo_documento")
@Getter
@Setter
public class TipoDocumentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoDocumento;
    private String codTipo;
    private String descTipo;
    private Integer estado;
    private String usuaCrea;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;

}
