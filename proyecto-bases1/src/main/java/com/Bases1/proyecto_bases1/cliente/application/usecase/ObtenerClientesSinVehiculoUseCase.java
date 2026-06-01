package com.Bases1.proyecto_bases1.cliente.application.usecase;

import com.Bases1.proyecto_bases1.cliente.domain.model.Cliente;
import com.Bases1.proyecto_bases1.cliente.domain.repository.ClienteRepository;

import java.util.List;

public class ObtenerClientesSinVehiculoUseCase {

    private final ClienteRepository repository;

    public ObtenerClientesSinVehiculoUseCase(
            ClienteRepository repository) {

        this.repository = repository;
    }

    public List<Cliente> ejecutar() {
        return repository.obtenerClientesSinVehiculo();
    }
}