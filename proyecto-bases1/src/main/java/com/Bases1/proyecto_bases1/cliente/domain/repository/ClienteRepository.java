package com.Bases1.proyecto_bases1.cliente.domain.repository;

import com.Bases1.proyecto_bases1.cliente.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    Cliente save(Cliente cliente);

    Optional<Cliente> findById(Long id);

    List<Cliente> findAll();

    void deleteById(Long id);

    // CONSULTAS DEL PROYECTO

    List<Cliente> obtenerClientesSinVehiculo();

    Cliente obtenerClienteMasActivo();
}