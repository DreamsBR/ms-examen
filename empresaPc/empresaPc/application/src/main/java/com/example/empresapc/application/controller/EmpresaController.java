package com.example.empresapc.application.controller;

import com.example.empresapc.domain.aggregates.dto.EmpresaDTO;
import com.example.empresapc.domain.aggregates.request.RequestEmpresa;
import com.example.empresapc.domain.ports.in.EmpresaServiceIn;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2/empresa")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaServiceIn empresaServiceIn;


    @PostMapping
    public ResponseEntity<EmpresaDTO> registrar(@RequestBody RequestEmpresa requestPersona){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.crearEmpresaIn(requestPersona));
    }

    @Operation(summary = "Obtener una Persona por ID")

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO>obtenerPersona(@PathVariable String id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.obtenerEmpresaIn(id).get());

    }
    @Operation(summary = "Obtener todas las Peronas")

    @GetMapping()
    public ResponseEntity<List<EmpresaDTO>>obtenerTodos(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.obtenerTodosIn());

    }

    //implementa findEmpresaExist del empresaServiceIn

    @Operation(summary = "Obtener todas las Empresas")
    @GetMapping("/exist")
    public ResponseEntity<List<EmpresaDTO>>findEmpresaExist(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.findEmpresaExistIn());

    }







    /*
    @Operation(summary = "Obtener Personas x DNI")
    @GetMapping("/bydni/{dni}")
    public ResponseEntity<Optional<EmpresaDTO>> getByDni(@PathVariable("dni") String dni){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.(dni));

    }

     */
    @Operation(summary = "Actualizar Personas x DNI")
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO>actualizar(@PathVariable Long id,@RequestBody RequestEmpresa requestPersona){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.actualizarIn(id,requestPersona));

    }

    @Operation(summary = "Eliminar Personas x DNI")
    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaDTO>deleteLog(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.deleteIn(id));

    }


}
