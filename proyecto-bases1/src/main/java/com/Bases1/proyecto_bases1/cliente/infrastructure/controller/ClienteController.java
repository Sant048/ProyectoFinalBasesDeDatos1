package com.Bases1.proyecto_bases1.cliente.infrastructure.controller;

import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteActividadResponse;
import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteDocumentoResponse;
import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteVehiculosResponse;
import com.Bases1.proyecto_bases1.cliente.application.dto.CrearClienteRequest;
import com.Bases1.proyecto_bases1.cliente.application.service.ClienteService;
import com.Bases1.proyecto_bases1.cliente.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService service;

    /**
     * Endpoint requerido por el frontend
     * GET /api/clientes
     */
    @GetMapping
    public List<ClienteDocumentoResponse> listar() {
        return service.listarClientes();
    }

    /**
     * Endpoint requerido por el frontend
     * POST /api/clientes
     */
    @PostMapping
    public Cliente crear(
            @RequestBody CrearClienteRequest request) {

        return service.guardar(request);
    }

    /**
     * Consulta SQL #2
     */
    @GetMapping("/documento/{numero}")
    public ClienteDocumentoResponse buscarPorDocumento(
            @PathVariable String numero) {

        return service.buscarPorDocumento(numero);
    }

    /**
     * Consulta SQL #3
     */
    @GetMapping("/con-vehiculos")
    public List<ClienteVehiculosResponse> conVehiculos() {
        return service.clientesConVehiculos();
    }

    /**
     * Consulta SQL #4
     */
    @GetMapping("/sin-vehiculos")
    public List<ClienteVehiculosResponse> sinVehiculos() {
        return service.clientesSinVehiculos();
    }

    /**
     * Consulta SQL #5
     */
    @GetMapping("/top-cliente")
    public ClienteActividadResponse topCliente() {
        return service.clienteConMasVehiculos();
    }
}