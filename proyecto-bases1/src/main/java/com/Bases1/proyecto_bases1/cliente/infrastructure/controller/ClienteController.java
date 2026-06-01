package com.Bases1.proyecto_bases1.cliente.infrastructure.controller;
import com.Bases1.proyecto_bases1.cliente.application.service.ClienteService;
import com.Bases1.proyecto_bases1.cliente.domain.model.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Cliente obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public Cliente crear(@RequestBody Cliente cliente) {
        return service.crear(cliente);
    }

    @PutMapping("/{id}")
    public Cliente actualizar(
            @PathVariable Long id,
            @RequestBody Cliente cliente) {

        return service.actualizar(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // CONSULTAS DEL PROYECTO

    @GetMapping("/sin-vehiculo")
    public List<Cliente> clientesSinVehiculo() {
        return service.obtenerClientesSinVehiculo();
    }

    @GetMapping("/mas-activo")
    public Cliente clienteMasActivo() {
        return service.obtenerClienteMasActivo();
    }
}