package com.example.empresapc.infraestructure.repository;

import com.example.empresapc.infraestructure.entity.TipoDocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity, Long> {

    TipoDocumentoEntity findByCodTipo(@Param("codTipo") String codTipo);

}
