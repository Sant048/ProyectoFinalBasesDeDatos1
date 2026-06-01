package com.Bases1.proyecto_bases1.cliente.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteResponse {

    private Integer idCliente;

    private String nombres;

    private String apellidos;

    private String numeroDocumento;

    private String telefono;

    private String correo;

    private String direccion;
}