package com.Bases1.proyecto_bases1.ordenTrabajo.application.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdenTrabajoResponse {

    private Long idOrden;
    private String placa;
    private String cliente;
    private String mecanico;
    private LocalDate fechaIngreso;
    private LocalDate fechaEstimada;
    private String descripcion;
    private String estado;
}