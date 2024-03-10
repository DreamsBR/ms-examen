package com.example.empresapc.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "tipo_empresa")
public class TipoEmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoEmpresa;
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
