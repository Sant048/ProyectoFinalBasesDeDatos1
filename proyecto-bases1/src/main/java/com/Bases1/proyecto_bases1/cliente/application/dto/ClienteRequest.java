package com.Bases1.proyecto_bases1.cliente.application.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteRequest {

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    @NotBlank
    private String numeroDocumento;

    private Integer idTipoDocumento;

    private String telefono;

    private String correo;

    private String direccion;
}