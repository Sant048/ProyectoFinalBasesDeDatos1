package com.Bases1.proyecto_bases1.Mecanico.aplicacion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MecanicoResponse {

    private Long idMecanico;

    private String nombres;

    private String apellidos;

    private String tipoDocumento;

    private String numeroDocumento;

    private String especialidad;

    private String telefono;

    private LocalDate fechaIngreso;

    private String estado;
}