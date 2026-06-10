package com.Bases1.proyecto_bases1.cliente.application.service;

import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteActividadResponse;
import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteDocumentoResponse;
import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteVehiculosResponse;
import com.Bases1.proyecto_bases1.cliente.application.dto.CrearClienteRequest;
import com.Bases1.proyecto_bases1.cliente.domain.model.Cliente;
import com.Bases1.proyecto_bases1.cliente.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    // CRUD

    public Cliente guardar(CrearClienteRequest request) {

        Cliente cliente = Cliente.builder()
                .nombres(request.nombres())
                .apellidos(request.apellidos())
                .idTipoDocumento(Long.valueOf(request.idTipoDocumento()))
                .numeroDocumento(request.numeroDocumento())
                .telefono(request.telefono())
                .correo(request.correo())
                .direccion(request.direccion())
                .fechaRegistro(LocalDate.now())
                .build();

        return clienteRepository.guardar(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.listarTodos();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.buscarPorId(id)
                .orElseThrow(() ->
                        new RuntimeException("Cliente no encontrado"));
    }

    public void eliminar(Long id) {
        clienteRepository.eliminar(id);
    }

    // CONSULTAS SQL

    public List<ClienteDocumentoResponse> listarClientes() {
        return clienteRepository.listarClientesConDocumento();
    }

    public ClienteDocumentoResponse buscarPorDocumento(String documento) {

        return clienteRepository
                .buscarPorDocumento(documento)
                .orElseThrow(() ->
                        new RuntimeException("Cliente no encontrado"));
    }

    public List<ClienteVehiculosResponse> clientesConVehiculos() {
        return clienteRepository.clientesConVehiculos();
    }

    public List<ClienteVehiculosResponse> clientesSinVehiculos() {
        return clienteRepository.clientesSinVehiculos();
    }

    public ClienteActividadResponse clienteConMasVehiculos() {

        return clienteRepository
                .clienteConMasVehiculos()
                .orElseThrow(() ->
                        new RuntimeException("No existen registros"));
    }
}