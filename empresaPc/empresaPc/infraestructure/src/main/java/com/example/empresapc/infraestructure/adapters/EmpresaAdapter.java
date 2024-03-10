package com.example.empresapc.infraestructure.adapters;

import com.example.empresapc.domain.aggregates.constants.Constants;
import com.example.empresapc.domain.aggregates.dto.EmpresaDTO;
import com.example.empresapc.domain.aggregates.request.RequestEmpresa;
import com.example.empresapc.domain.aggregates.response.ResponseSunat;
import com.example.empresapc.domain.ports.out.EmpresaServiceOut;
import com.example.empresapc.infraestructure.entity.EmpresaEntity;
import com.example.empresapc.infraestructure.entity.TipoDocumentoEntity;
import com.example.empresapc.infraestructure.entity.TipoEmpresaEntity;
import com.example.empresapc.infraestructure.mapper.EmpresaMapper;
import com.example.empresapc.infraestructure.repository.EmpresaRepository;
import com.example.empresapc.infraestructure.repository.TipoDocumentoRepository;
import com.example.empresapc.infraestructure.repository.TipoEmpresaRepository;
import com.example.empresapc.infraestructure.rest.client.EmpresaSunat;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EmpresaAdapter implements EmpresaServiceOut {

    private final EmpresaRepository empresaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final TipoEmpresaRepository tipoEmpresaRepository;
    private final EmpresaSunat sunat;
    private final EmpresaMapper empresaMapper;

    @Value("${token_api}")
    private String tokenApi;

    @Override
    public EmpresaDTO crearEmpresaOut(RequestEmpresa requestEmpresa) {
        ResponseSunat responseSunat = getExecutionSunat(requestEmpresa.getNumDoc());
        EmpresaEntity empresaEntity = getEmpresaEntity(responseSunat, requestEmpresa);
        empresaRepository.save(empresaEntity);
        return empresaMapper.mapToDto(empresaEntity);
    }

    @Override
    public Optional<EmpresaDTO> obtenerEmpresaOut(String numDoc) {
        return Optional.ofNullable(empresaMapper.mapToDto(empresaRepository.findByNumDocu(numDoc)));
    }

    @Override
    public List<EmpresaDTO> obtenerTodosOut() {
        List<EmpresaDTO> empresaDTOS = new ArrayList<>();
        List<EmpresaEntity> entityList = empresaRepository.findByEstado(Constants.STATUS_ACTIVE);
        for (EmpresaEntity entity : entityList) {
            empresaDTOS.add(empresaMapper.mapToDto(entity));
        }
        return empresaDTOS;
    }

    @Override
    public EmpresaDTO actualizarOut(Long id, RequestEmpresa requestEmpresa) {
        Optional<EmpresaEntity> empresaRecuperada = empresaRepository.findById(id);
        if (empresaRecuperada.isPresent()) {
            ResponseSunat responseSunat = getExecutionSunat(requestEmpresa.getNumDoc());
            EmpresaEntity empresaActualizada = getEmpresaEntityUpdate(responseSunat, empresaRecuperada.get(), requestEmpresa);
            empresaRepository.save(empresaActualizada);
            return empresaMapper.mapToDto(empresaActualizada);
        }
        return null;
    }


    @Override
    public EmpresaDTO deleteOut(Long id) {
        boolean existe = empresaRepository.existsById(id);
        if(existe){
            Optional<EmpresaEntity> empresaRecuperada = empresaRepository.findById(id);
            empresaRecuperada.get().setEstado(Constants.STATUS_INACTIVE);
            empresaRecuperada.get().setUsuaModif(Constants.AUDIT_ADMIN);
            empresaRecuperada.get().setDateModif(getTimestamp());
            empresaRepository.save(empresaRecuperada.get());
            return empresaMapper.mapToDto(empresaRecuperada.get());
        }
        return null;
    }

    @Override
    public List<EmpresaDTO> findEmpresaExistOut(){
        List<EmpresaDTO> empresaDTOS = new ArrayList<>();
        List<EmpresaEntity> entityList = empresaRepository.findByEstado(Constants.STATUS_ACTIVE);
        for (EmpresaEntity entity : entityList) {
            empresaDTOS.add(empresaMapper.mapToDto(entity));
        }
        return empresaDTOS;
    }

    public ResponseSunat getExecutionSunat(String numero){
        String authorization = "Bearer " + tokenApi;
        ResponseSunat responseReniec = sunat.getInfoSunat(numero,authorization);
        return  responseReniec;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }

    private EmpresaEntity getEmpresaEntity(ResponseSunat responseSunat, RequestEmpresa requestEmpresa) {
        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findByCodTipo(requestEmpresa.getTipoDoc());
        TipoEmpresaEntity tipoEmpresa = tipoEmpresaRepository.findByCodTipo(requestEmpresa.getTipoEmp());
        EmpresaEntity empresaEntity = new EmpresaEntity();
        empresaEntity.setRazonSocial(responseSunat.getRazonSocial());
        empresaEntity.setNumDocu(responseSunat.getNumeroDocumento());
        empresaEntity.setEstado(Constants.STATUS_ACTIVE);
        empresaEntity.setDateCreate(getTimestamp());
        empresaEntity.setUsuaCrea(Constants.AUDIT_ADMIN);
        empresaEntity.setNomComercial(responseSunat.getRazonSocial());
        empresaEntity.setTipoEmpresa(tipoEmpresa);
        empresaEntity.setTipoDocumento(tipoDocumento);
        return empresaEntity;
    }

    private EmpresaEntity getEmpresaEntityUpdate(ResponseSunat responseSunat, EmpresaEntity empresaActualizada, RequestEmpresa requestEmpresa) {
        empresaActualizada.setRazonSocial(responseSunat.getRazonSocial());
        empresaActualizada.setNumDocu(responseSunat.getNumeroDocumento());
        empresaActualizada.setUsuaModif(Constants.AUDIT_ADMIN);
        empresaActualizada.setDateModif(getTimestamp());
        return empresaActualizada;
    }


}
