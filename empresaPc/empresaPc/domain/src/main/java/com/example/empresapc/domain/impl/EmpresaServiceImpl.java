package com.example.empresapc.domain.impl;

import com.example.empresapc.domain.aggregates.dto.EmpresaDTO;
import com.example.empresapc.domain.aggregates.request.RequestEmpresa;
import com.example.empresapc.domain.ports.in.EmpresaServiceIn;
import com.example.empresapc.domain.ports.out.EmpresaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaServiceIn {


    private final EmpresaServiceOut empresaServiceOut;

    @Override
    public EmpresaDTO crearEmpresaIn(RequestEmpresa requestEmpresa) {
        return empresaServiceOut.crearEmpresaOut(requestEmpresa);
    }

    @Override
    public Optional<EmpresaDTO> obtenerEmpresaIn(String numDoc) {
        return empresaServiceOut.obtenerEmpresaOut(numDoc);
    }

    @Override
    public List<EmpresaDTO> obtenerTodosIn() {
        return empresaServiceOut.obtenerTodosOut();
    }

    @Override
    public EmpresaDTO actualizarIn(Long id, RequestEmpresa requestPersona) {
        return empresaServiceOut.actualizarOut(id, requestPersona);
    }

    @Override
    public EmpresaDTO deleteIn(Long id) {
        return empresaServiceOut.deleteOut(id);
    }

    @Override
    public List<EmpresaDTO> findEmpresaExistIn() {
        return empresaServiceOut.findEmpresaExistOut();
    }
}
