package com.example.empresapc.infraestructure.mapper;

import com.example.empresapc.domain.aggregates.dto.EmpresaDTO;
import com.example.empresapc.infraestructure.entity.EmpresaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmpresaMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public EmpresaDTO mapToDto(EmpresaEntity empresaEntity){
        return modelMapper.map(empresaEntity, EmpresaDTO.class);
    }

    public EmpresaEntity mapToEntity(EmpresaDTO empresaDTO){
        return modelMapper.map(empresaDTO, EmpresaEntity.class );
    }
}
