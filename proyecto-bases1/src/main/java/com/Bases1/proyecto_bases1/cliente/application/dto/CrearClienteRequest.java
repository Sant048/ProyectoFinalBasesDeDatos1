package com.Bases1.proyecto_bases1.cliente.application.dto;

public record CrearClienteRequest(
        String nombres,
        String apellidos,
        Integer idTipoDocumento,
        String numeroDocumento,
        String telefono,
        String correo,
        String direccion
) {}
