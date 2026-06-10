package com.Bases1.proyecto_bases1.cliente.application.dto;

import java.time.LocalDate;

public record ClienteDocumentoResponse(
        Long idCliente,
        String nombres,
        String apellidos,
        String tipoDocumento,
        String numeroDocumento,
        String telefono,
        String correo,
        String direccion,
        LocalDate fechaRegistro
) {}