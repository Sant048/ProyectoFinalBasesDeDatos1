package com.Bases1.proyecto_bases1.Vehiculo.Infraestructura.controller;


import com.Bases1.proyecto_bases1.Vehiculo.aplicacion.dto.CrearVehiculoRequest;
import com.Bases1.proyecto_bases1.Vehiculo.aplicacion.dto.HistorialVehiculoResponse;
import com.Bases1.proyecto_bases1.Vehiculo.aplicacion.dto.VehiculoResponse;
import com.Bases1.proyecto_bases1.Vehiculo.aplicacion.service.VehiculoService;
import com.Bases1.proyecto_bases1.Vehiculo.dominio.modelo.Vehiculo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VehiculoController {

    private final VehiculoService service;

    /**
     * Endpoint requerido por frontend
     */
    @GetMapping
    public List<VehiculoResponse> listar() {
        return service.listarVehiculos();
    }

    /**
     * Endpoint requerido por frontend
     */
    @PostMapping
    public Vehiculo crear(
            @RequestBody CrearVehiculoRequest request) {

        return service.guardar(request);
    }

    /**
     * Endpoint requerido por frontend
     */
    @DeleteMapping("/{placa}")
    public void eliminar(
            @PathVariable String placa) {

        service.eliminar(placa);
    }

    /**
     * Endpoint requerido por frontend
     */
    @GetMapping("/{placa}/historial")
    public List<HistorialVehiculoResponse>
    historial(@PathVariable String placa) {

        return service.historialVehiculo(placa);
    }
}