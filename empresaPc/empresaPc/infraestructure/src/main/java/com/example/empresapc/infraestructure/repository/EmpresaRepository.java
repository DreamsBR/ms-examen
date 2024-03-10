package com.example.empresapc.infraestructure.repository;

import com.example.empresapc.infraestructure.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {

    List<EmpresaEntity> findEmpresaExist();

    EmpresaEntity findByNumDocu(@Param("numDocu") String numDocu);

    List<EmpresaEntity> findByEstado(@Param("estado") Integer estado);

}
