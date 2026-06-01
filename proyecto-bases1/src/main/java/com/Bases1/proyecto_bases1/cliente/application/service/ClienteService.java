package com.Bases1.proyecto_bases1.cliente.application.service;


import com.Bases1.proyecto_bases1.cliente.domain.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ClienteService {

    Cliente crear(Cliente cliente);

    Cliente obtener(Long id);

    List<Cliente> listar();

    Cliente actualizar(Long id, Cliente cliente);

    void eliminar(Long id);

    List<Cliente> obtenerClientesSinVehiculo();

    Cliente obtenerClienteMasActivo();
}