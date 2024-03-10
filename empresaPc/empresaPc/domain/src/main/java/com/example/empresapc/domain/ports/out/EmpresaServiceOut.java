package com.example.empresapc.domain.ports.out;

import com.example.empresapc.domain.aggregates.dto.EmpresaDTO;
import com.example.empresapc.domain.aggregates.request.RequestEmpresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceOut {
    EmpresaDTO crearEmpresaOut(RequestEmpresa requestEmpresa);
    Optional<EmpresaDTO> obtenerEmpresaOut(String numDoc);
    List<EmpresaDTO> obtenerTodosOut();
    EmpresaDTO actualizarOut(Long id, RequestEmpresa requestEmpresa);
    EmpresaDTO deleteOut(Long id);

    List<EmpresaDTO> findEmpresaExistOut();

}
