package com.Bases1.proyecto_bases1.cliente.application.usecase;

import com.Bases1.proyecto_bases1.cliente.domain.model.Cliente;
import com.Bases1.proyecto_bases1.cliente.domain.repository.ClienteRepository;

public class ObtenerClienteMasActivoUseCase {

    private final ClienteRepository repository;

    public ObtenerClienteMasActivoUseCase(
            ClienteRepository repository) {

        this.repository = repository;
    }

    public Cliente ejecutar() {
        return repository.obtenerClienteMasActivo();
    }
}
