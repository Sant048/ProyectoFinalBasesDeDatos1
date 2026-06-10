package com.Bases1.proyecto_bases1.ordenTrabajo.application.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CrearOrdenTrabajoRequest {

    private Long idVehiculo;
    private Long idMecanico;
    private LocalDate fechaIngreso;
    private LocalDate fechaEstimadaEntrega;
    private LocalDate fechaRealEntrega;
    private String descripcionFalla;
    private Long idEstadoOrden;
}