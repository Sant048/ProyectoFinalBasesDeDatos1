package com.Bases1.proyecto_bases1.reporte.infrastructure.controller;

import com.Bases1.proyecto_bases1.reporte.application.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReporteController {

    private final ReporteService service;

    @GetMapping("/resumen")
    public Object resumen() {
        return service.resumen();
    }

    @GetMapping("/ordenes-por-estado")
    public Object estados() {
        return service.estados();
    }

    @GetMapping("/top-mecanicos")
    public Object topMecanicos() {
        return service.topMecanicos();
    }

    @GetMapping("/ingresos-por-mes")
    public Object ingresosMes() {
        return service.ingresosMes();
    }

    @GetMapping("/top-clientes")
    public Object topClientes() {
        return service.topClientes();
    }

    @GetMapping("/rendimiento-mecanicos")
    public Object rendimiento() {
        return service.rendimiento();
    }

    @GetMapping("/top-servicios")
    public Object topServicios() {
        return service.topServicios();
    }
}