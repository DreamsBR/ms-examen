package com.example.empresapc.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Getter
@Setter
@NamedQuery(name = "EmpresaEntity.findEmpresaExist", query = "select a from EmpresaEntity a where a.estado=1 ")
@Table(name = "empresa")
public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresa;
    private String numDocu;
    private String razonSocial;
    private String nomComercial;
    private Integer estado;
    private String usuaCrea;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;



    @ManyToOne
    @JoinColumn(name = "tipo_documento_id", nullable = false)
    private TipoDocumentoEntity tipoDocumento;

    @ManyToOne
    @JoinColumn(name = "tipo_empresa_id", nullable = false)
    private TipoEmpresaEntity tipoEmpresa;

}
