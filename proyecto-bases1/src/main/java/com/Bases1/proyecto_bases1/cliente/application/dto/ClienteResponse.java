package com.Bases1.proyecto_bases1.cliente.application.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
public record ClienteResponse(
        Integer idCliente,
        String nombres,
        String apellidos,
        String tipoDocumento,
        String numeroDocumento,
        String telefono,
        String correo,
        LocalDate fechaRegistro
) {}