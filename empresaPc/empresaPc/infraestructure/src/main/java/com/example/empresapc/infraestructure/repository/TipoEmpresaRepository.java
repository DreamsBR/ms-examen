package com.example.empresapc.infraestructure.repository;

import com.example.empresapc.infraestructure.entity.TipoEmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TipoEmpresaRepository extends JpaRepository<TipoEmpresaEntity, Long> {

    TipoEmpresaEntity findByCodTipo(@Param("x") String codTipo);

}
