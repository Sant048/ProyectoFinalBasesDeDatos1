package com.Bases1.proyecto_bases1.proveedor.infrastructure.controller;

import com.Bases1.proyecto_bases1.proveedor.application.dto.CrearProveedorRequest;
import com.Bases1.proyecto_bases1.proveedor.application.dto.ProveedorResponse;
import com.Bases1.proyecto_bases1.proveedor.application.service.ProveedorService;
import com.Bases1.proyecto_bases1.proveedor.domain.model.Proveedor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario/proveedores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProveedorController {

    private final ProveedorService service;

    @GetMapping
    public List<ProveedorResponse> listar(){
        return service.listar();
    }

    @PostMapping
    public Proveedor crear(
            @RequestBody CrearProveedorRequest request){

        return service.guardar(request);
    }

    @DeleteMapping("/{nit}")
    public void eliminar(
            @PathVariable String nit){

        service.eliminar(nit);
    }
}