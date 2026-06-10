package com.Bases1.proyecto_bases1.cliente.application.dto;

public record ClienteVehiculoResponse(
        Integer idCliente,
        String nombres,
        String apellidos,
        String telefono,
        String correo
) {}