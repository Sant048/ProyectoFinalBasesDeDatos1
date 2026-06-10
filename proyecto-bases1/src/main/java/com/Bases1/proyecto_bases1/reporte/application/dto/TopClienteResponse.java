package com.Bases1.proyecto_bases1.reporte.application.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopClienteResponse {

    private String cliente;
    private String telefono;
    private String correo;
    private Long totalOrdenes;
    private Double gastoTotal;
}