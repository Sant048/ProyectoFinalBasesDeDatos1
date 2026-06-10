package com.Bases1.proyecto_bases1.ordenTrabajo.infrastructure.controller;

import com.Bases1.proyecto_bases1.ordenTrabajo.application.dto.*;
import com.Bases1.proyecto_bases1.ordenTrabajo.application.service.OrdenTrabajoService;
import com.Bases1.proyecto_bases1.ordenTrabajo.domain.model.OrdenTrabajo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrdenTrabajoController {

    private final OrdenTrabajoService service;

    @GetMapping
    public List<OrdenTrabajoResponse> listar() {
        return service.listar();
    }

    @PostMapping
    public OrdenTrabajo crear(
            @RequestBody CrearOrdenTrabajoRequest request) {

        return service.guardar(request);
    }

    @GetMapping("/abiertas")
    public List<OrdenTrabajoResponse> abiertas() {
        return service.abiertas();
    }

    @GetMapping("/cerradas")
    public List<OrdenTrabajoResponse> cerradas() {
        return service.cerradas();
    }

    @GetMapping("/top-mecanico")
    public OrdenTopMecanicoResponse topMecanico() {
        return service.topMecanico();
    }
}