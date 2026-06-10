package com.Bases1.proyecto_bases1.reporte.application.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RendimientoMecanicoResponse {

    private String mecanico;
    private String especialidad;
    private Long ordenesAtendidas;
    private Double ingresosGenerados;
    private Double ingresoPromedio;
    private Double diasPromedioEntrega;
}