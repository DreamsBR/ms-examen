package com.example.empresapc.domain.ports.in;

import com.example.empresapc.domain.aggregates.dto.EmpresaDTO;
import com.example.empresapc.domain.aggregates.request.RequestEmpresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceIn {
    EmpresaDTO crearEmpresaIn(RequestEmpresa requestEmpresa);
    Optional<EmpresaDTO> obtenerEmpresaIn(String numDoc);
    List<EmpresaDTO> obtenerTodosIn();
    EmpresaDTO actualizarIn(Long id, RequestEmpresa requestPersona);
    EmpresaDTO deleteIn(Long id);

    List<EmpresaDTO> findEmpresaExistIn();
}
