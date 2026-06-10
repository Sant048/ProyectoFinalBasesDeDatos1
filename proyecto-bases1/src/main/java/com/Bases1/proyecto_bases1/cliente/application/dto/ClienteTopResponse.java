package com.Bases1.proyecto_bases1.cliente.application.dto;

public record ClienteTopResponse(
        Integer idCliente,
        String nombres,
        String apellidos,
        Long totalVehiculos
) {}
