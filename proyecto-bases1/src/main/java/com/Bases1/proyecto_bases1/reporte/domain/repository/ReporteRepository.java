package com.Bases1.proyecto_bases1.reporte.domain.repository;

import com.Bases1.proyecto_bases1.reporte.application.dto.*;

import java.util.List;

public interface ReporteRepository {

    ResumenDashboardResponse resumen();

    List<OrdenEstadoResponse> ordenesPorEstado();

    List<TopMecanicoResponse> topMecanicos();

    List<IngresosMesResponse> ingresosPorMes();

    List<TopClienteResponse> topClientes();

    List<RendimientoMecanicoResponse> rendimientoMecanicos();
    List<TopServicioResponse> topServicios();

}