package com.Bases1.proyecto_bases1.ordenTrabajo.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdenTopMecanicoResponse {

    private Long idMecanico;
    private String nombres;
    private String apellidos;
    private Long totalOrdenes;
}