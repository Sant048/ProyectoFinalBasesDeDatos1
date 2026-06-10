package com.Bases1.proyecto_bases1.ordenTrabajo.domain.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenTrabajo {

    private Long idOrden;
    private Long idVehiculo;
    private Long idMecanico;
    private LocalDate fechaIngreso;
    private LocalDate fechaEstimadaEntrega;
    private LocalDate fechaRealEntrega;
    private String descripcionFalla;
    private Long idEstadoOrden;
}