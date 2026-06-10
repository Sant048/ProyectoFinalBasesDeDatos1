package com.Bases1.proyecto_bases1.repuesto.infrastructure.controller;

import com.Bases1.proyecto_bases1.repuesto.application.dto.*;
import com.Bases1.proyecto_bases1.repuesto.application.service.RepuestoService;
import com.Bases1.proyecto_bases1.repuesto.domain.model.Repuesto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario/repuestos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RepuestoController {

    private final RepuestoService service;

    @GetMapping
    public List<RepuestoResponse> listar(){
        return service.listar();
    }

    @GetMapping("/criticos")
    public List<RepuestoResponse> criticos(){
        return service.criticos();
    }

    @PostMapping
    public Repuesto crear(
            @RequestBody CrearRepuestoRequest request){

        return service.guardar(request);
    }

    @DeleteMapping("/{referencia}")
    public void eliminar(
            @PathVariable String referencia){

        service.eliminar(referencia);
    }
}