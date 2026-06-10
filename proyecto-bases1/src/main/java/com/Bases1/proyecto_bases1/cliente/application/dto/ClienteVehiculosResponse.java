package com.Bases1.proyecto_bases1.cliente.application.dto;

public record ClienteVehiculosResponse(
        Long idCliente,
        String nombres,
        String apellidos,
        String telefono,
        String correo
) {}
