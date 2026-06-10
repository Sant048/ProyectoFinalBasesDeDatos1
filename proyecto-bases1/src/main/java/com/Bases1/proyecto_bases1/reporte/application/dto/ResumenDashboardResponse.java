package com.Bases1.proyecto_bases1.reporte.application.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumenDashboardResponse {

    private Long totalClientes;
    private Long totalVehiculos;
    private Long mecanicosActivos;
    private Long ordenesActivas;
    private Double ingresosTotales;
    private Long repuestosCriticos;
}