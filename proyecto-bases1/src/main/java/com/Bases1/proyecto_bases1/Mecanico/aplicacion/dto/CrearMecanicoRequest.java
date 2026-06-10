package com.Bases1.proyecto_bases1.Mecanico.aplicacion.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CrearMecanicoRequest {

    private String nombres;

    private String apellidos;

    private Long idTipoDocumento;

    private String numeroDocumento;

    private Long idEspecialidad;

    private String telefono;

    private LocalDate fechaIngreso;
}