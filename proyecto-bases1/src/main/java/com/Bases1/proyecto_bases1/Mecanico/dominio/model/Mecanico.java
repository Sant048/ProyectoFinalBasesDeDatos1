package com.Bases1.proyecto_bases1.Mecanico.dominio.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mecanico {

    private Long idMecanico;

    private String nombres;

    private String apellidos;

    private String numeroDocumento;

    private Long idTipoDocumento;

    private Long idEspecialidad;

    private String telefono;

    private LocalDate fechaIngreso;

    private Long idEstadoMecanico;
}