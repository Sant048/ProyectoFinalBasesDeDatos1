package com.Bases1.proyecto_bases1.reporte.infrastructure.persistence.repository;

import com.Bases1.proyecto_bases1.reporte.application.dto.*;
import com.Bases1.proyecto_bases1.reporte.domain.repository.ReporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReporteRepositoryAdapter implements ReporteRepository {

    private final JpaReporteRepository jpaReporteRepository;

    @Override
    public ResumenDashboardResponse resumen() {

        var r = jpaReporteRepository.resumen();

        return new ResumenDashboardResponse(
                ((Number) r.get("totalClientes")).longValue(),
                ((Number) r.get("totalVehiculos")).longValue(),
                ((Number) r.get("mecanicosActivos")).longValue(),
                ((Number) r.get("ordenesActivas")).longValue(),
                ((Number) r.get("ingresosTotales")).doubleValue(),
                ((Number) r.get("repuestosCriticos")).longValue()
        );
    }

    @Override
    public List<OrdenEstadoResponse> ordenesPorEstado() {

        return jpaReporteRepository.ordenesPorEstado()
                .stream()
                .map(r -> new OrdenEstadoResponse(
                        (String) r.get("nombreEstado"),
                        ((Number) r.get("total")).longValue()
                ))
                .toList();
    }

    @Override
    public List<TopMecanicoResponse> topMecanicos() {

        return jpaReporteRepository.topMecanicos()
                .stream()
                .map(r -> new TopMecanicoResponse(
                        (String) r.get("mecanico"),
                        ((Number) r.get("ingresosGenerados")).doubleValue()
                ))
                .toList();
    }

    @Override
    public List<IngresosMesResponse> ingresosPorMes() {

        return jpaReporteRepository.ingresosPorMes()
                .stream()
                .map(r -> new IngresosMesResponse(
                        (String) r.get("periodo"),
                        ((Number) r.get("ingresosMes")).doubleValue()
                ))
                .toList();
    }

    @Override
    public List<TopClienteResponse> topClientes() {

        return jpaReporteRepository.topClientes()
                .stream()
                .map(r -> new TopClienteResponse(
                        (String) r.get("cliente"),
                        (String) r.get("telefono"),
                        (String) r.get("correo"),
                        ((Number) r.get("totalOrdenes")).longValue(),
                        ((Number) r.get("gastoTotal")).doubleValue()
                ))
                .toList();
    }

    @Override
    public List<RendimientoMecanicoResponse> rendimientoMecanicos() {

        return jpaReporteRepository.rendimientoMecanicos()
                .stream()
                .map(r -> new RendimientoMecanicoResponse(
                        (String) r.get("mecanico"),
                        (String) r.get("especialidad"),
                        ((Number) r.get("ordenesAtendidas")).longValue(),
                        ((Number) r.get("ingresosGenerados")).doubleValue(),
                        ((Number) r.get("ingresoPromedio")).doubleValue(),
                        r.get("diasPromedioEntrega") == null
                                ? 0.0
                                : ((Number) r.get("diasPromedioEntrega")).doubleValue()
                ))
                .toList();
    }

    @Override
    public List<TopServicioResponse> topServicios() {
        return jpaReporteRepository.topServicios()
                .stream()
                .map(r -> new TopServicioResponse(
                        (String)  r.get("nombreServicio"),
                        ((Number) r.get("vecesSolicitado")).longValue(),
                        ((Number) r.get("ingresosGenerados")).doubleValue()
                ))
                .toList();
    }
}