package com.Bases1.proyecto_bases1.cliente.infrastructure.persistence.mapper;


import com.Bases1.proyecto_bases1.cliente.domain.model.Cliente;
import com.Bases1.proyecto_bases1.cliente.infrastructure.persistence.entity.ClienteEntity;

public class ClienteMapper {

    private ClienteMapper() {
    }

    public static Cliente toDomain(ClienteEntity entity) {

        return new Cliente(
                entity.getIdCliente(),
                entity.getNombres(),
                entity.getApellidos(),
                entity.getNumeroDocumento(),
                entity.getIdTipoDocumento(),
                entity.getTelefono(),
                entity.getCorreo(),
                entity.getDireccion(),
                entity.getFechaRegistro()
        );
    }

    public static ClienteEntity toEntity(Cliente cliente) {

        return ClienteEntity.builder()
                .idCliente(cliente.getIdCliente())
                .nombres(cliente.getNombres())
                .apellidos(cliente.getApellidos())
                .numeroDocumento(cliente.getNumeroDocumento())
                .idTipoDocumento(cliente.getIdTipoDocumento())
                .telefono(cliente.getTelefono())
                .correo(cliente.getCorreo())
                .direccion(cliente.getDireccion())
                .fechaRegistro(cliente.getFechaRegistro())
                .build();
    }
}