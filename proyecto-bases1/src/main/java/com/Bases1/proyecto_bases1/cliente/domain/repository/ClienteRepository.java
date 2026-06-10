package com.Bases1.proyecto_bases1.cliente.domain.repository;

import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteActividadResponse;
import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteDocumentoResponse;
import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteVehiculosResponse;
import com.Bases1.proyecto_bases1.cliente.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    // =====================================
    // CRUD
    // =====================================

    Cliente guardar(Cliente cliente);

    Optional<Cliente> buscarPorId(Long id);

    List<Cliente> listarTodos();

    void eliminar(Long id);

    // =====================================
    // CONSULTAS SQL DEL PROYECTO
    // =====================================

    /**
     * Consulta #1
     * Listar todos los clientes con su tipo de documento
     */
    List<ClienteDocumentoResponse> listarClientesConDocumento();

    /**
     * Consulta #2
     * Buscar cliente por número de documento
     */
    Optional<ClienteDocumentoResponse> buscarPorDocumento(String documento);

    /**
     * Consulta #3
     * Clientes con al menos un vehículo registrado
     */
    List<ClienteVehiculosResponse> clientesConVehiculos();

    /**
     * Consulta #4
     * Clientes sin vehículo registrado
     */
    List<ClienteVehiculosResponse> clientesSinVehiculos();

    /**
     * Consulta #5
     * Cliente con mayor número de vehículos
     */
    Optional<ClienteActividadResponse> clienteConMasVehiculos();
}