package com.Bases1.proyecto_bases1.cliente.infrastructure.persistence.repository;

import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteActividadResponse;
import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteDocumentoResponse;
import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteVehiculosResponse;
import com.Bases1.proyecto_bases1.cliente.domain.model.Cliente;
import com.Bases1.proyecto_bases1.cliente.domain.repository.ClienteRepository;
import com.Bases1.proyecto_bases1.cliente.infrastructure.persistence.entity.ClienteEntity;
import com.Bases1.proyecto_bases1.cliente.infrastructure.persistence.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryAdapter implements ClienteRepository {

    private final JpaClienteRepository jpaRepository;

    @Override
    public Cliente guardar(Cliente cliente) {

        ClienteEntity entity =
                jpaRepository.save(ClienteMapper.toEntity(cliente));

        return ClienteMapper.toDomain(entity);
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {

        return jpaRepository.findById(id)
                .map(ClienteMapper::toDomain);
    }

    @Override
    public List<Cliente> listarTodos() {

        return jpaRepository.findAll()
                .stream()
                .map(ClienteMapper::toDomain)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<ClienteDocumentoResponse> listarClientesConDocumento() {

        return jpaRepository.listarClientesConDocumento()
                .stream()
                .map(this::mapClienteDocumento)
                .toList();
    }

    @Override
    public Optional<ClienteDocumentoResponse> buscarPorDocumento(String documento) {

        Map<String, Object> row =
                jpaRepository.buscarPorDocumento(documento);

        if (row == null) {
            return Optional.empty();
        }

        return Optional.of(mapClienteDocumento(row));
    }

    @Override
    public List<ClienteVehiculosResponse> clientesConVehiculos() {

        return jpaRepository.clientesConVehiculos()
                .stream()
                .map(this::mapClienteVehiculo)
                .toList();
    }

    @Override
    public List<ClienteVehiculosResponse> clientesSinVehiculos() {

        return jpaRepository.clientesSinVehiculos()
                .stream()
                .map(this::mapClienteVehiculo)
                .toList();
    }

    @Override
    public Optional<ClienteActividadResponse> clienteConMasVehiculos() {

        Map<String, Object> row =
                jpaRepository.clienteConMasVehiculos();

        if (row == null) {
            return Optional.empty();
        }

        return Optional.of(
                new ClienteActividadResponse(
                        ((Number) row.get("id_cliente")).longValue(),
                        (String) row.get("nombres"),
                        (String) row.get("apellidos"),
                        ((Number) row.get("total_vehiculos")).longValue()
                )
        );
    }

    private ClienteDocumentoResponse mapClienteDocumento(
            Map<String, Object> row) {

        return new ClienteDocumentoResponse(
                ((Number) row.get("id_cliente")).longValue(),
                (String) row.get("nombres"),
                (String) row.get("apellidos"),
                (String) row.get("sigla"),
                (String) row.get("numero_documento"),
                (String) row.get("telefono"),
                (String) row.get("correo"),
                (String) row.get("direccion"),
                convertirFecha(row.get("fecha_registro"))
        );
    }

    private ClienteVehiculosResponse mapClienteVehiculo(
            Map<String, Object> row) {

        return new ClienteVehiculosResponse(
                ((Number) row.get("id_cliente")).longValue(),
                (String) row.get("nombres"),
                (String) row.get("apellidos"),
                (String) row.get("telefono"),
                (String) row.get("correo")
        );
    }

    private LocalDate convertirFecha(Object fecha) {

        if (fecha == null) {
            return null;
        }

        if (fecha instanceof LocalDate localDate) {
            return localDate;
        }

        if (fecha instanceof java.sql.Date sqlDate) {
            return sqlDate.toLocalDate();
        }

        throw new IllegalArgumentException(
                "Tipo de fecha no soportado: "
                        + fecha.getClass().getName()
        );
    }
}