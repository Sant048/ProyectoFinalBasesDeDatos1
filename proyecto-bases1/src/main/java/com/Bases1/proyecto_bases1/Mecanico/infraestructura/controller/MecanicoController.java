package com.Bases1.proyecto_bases1.Mecanico.infraestructura.controller;


import com.Bases1.proyecto_bases1.Mecanico.aplicacion.dto.CrearMecanicoRequest;
import com.Bases1.proyecto_bases1.Mecanico.aplicacion.dto.MecanicoResponse;
import com.Bases1.proyecto_bases1.Mecanico.aplicacion.service.MecanicoService;
import com.Bases1.proyecto_bases1.Mecanico.dominio.model.Mecanico;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mecanicos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MecanicoController {

    private final MecanicoService service;

    @GetMapping
    public List<MecanicoResponse> listar() {
        return service.listar();
    }

    @PostMapping
    public Mecanico crear(
            @RequestBody CrearMecanicoRequest request) {

        return service.guardar(request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable Long id) {

        service.eliminar(id);
    }
}