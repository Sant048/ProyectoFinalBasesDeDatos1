package com.Bases1.proyecto_bases1.reporte.infrastructure.persistence.repository;


import com.Bases1.proyecto_bases1.ordenTrabajo.infrastructure.persistence.entity.OrdenTrabajoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface JpaReporteRepository
        extends JpaRepository<OrdenTrabajoEntity, Integer> {

    @Query(value = """
    SELECT
    (SELECT COUNT(*) FROM cliente) totalClientes,
    (SELECT COUNT(*) FROM vehiculo) totalVehiculos,
    (SELECT COUNT(*) FROM mecanico WHERE id_estado_mecanico=1) mecanicosActivos,
    (SELECT COUNT(*) FROM orden_trabajo WHERE id_estado_orden IN(1,2)) ordenesActivas,
    (SELECT COALESCE(SUM(precio_aplicado*cantidad),0) FROM detalle_orden) ingresosTotales,
    (SELECT COUNT(*) FROM repuesto WHERE stock_actual<=stock_minimo) repuestosCriticos
    """, nativeQuery = true)
    Map<String,Object> resumen();

    @Query(value = """
    SELECT
    eo.nombre_estado nombreEstado,
    COUNT(o.id_orden) total
    FROM estado_orden eo
    LEFT JOIN orden_trabajo o
    ON eo.id_estado_orden=o.id_estado_orden
    GROUP BY eo.nombre_estado
    """, nativeQuery = true)
    List<Map<String,Object>> ordenesPorEstado();

    @Query(value = """
    SELECT
    CONCAT(m.nombres,' ',m.apellidos) mecanico,
    SUM(d.precio_aplicado*d.cantidad) ingresosGenerados
    FROM mecanico m
    INNER JOIN orden_trabajo o ON m.id_mecanico=o.id_mecanico
    INNER JOIN detalle_orden d ON o.id_orden=d.id_orden
    GROUP BY m.id_mecanico
    ORDER BY ingresosGenerados DESC
    """, nativeQuery = true)
    List<Map<String,Object>> topMecanicos();

    @Query(value = """
    SELECT
    DATE_FORMAT(o.fecha_ingreso,'%Y-%m') periodo,
    SUM(d.precio_aplicado*d.cantidad) ingresosMes
    FROM orden_trabajo o
    INNER JOIN detalle_orden d ON o.id_orden=d.id_orden
    GROUP BY DATE_FORMAT(o.fecha_ingreso,'%Y-%m')
    ORDER BY periodo
    """, nativeQuery = true)
    List<Map<String,Object>> ingresosPorMes();

    @Query(value = """
    SELECT
    CONCAT(c.nombres,' ',c.apellidos) cliente,
    c.telefono,
    c.correo,
    COUNT(DISTINCT o.id_orden) totalOrdenes,
    SUM(d.precio_aplicado*d.cantidad) gastoTotal
    FROM cliente c
    INNER JOIN vehiculo v ON c.id_cliente=v.id_cliente
    INNER JOIN orden_trabajo o ON v.id_vehiculo=o.id_vehiculo
    INNER JOIN detalle_orden d ON o.id_orden=d.id_orden
    GROUP BY c.id_cliente
    ORDER BY gastoTotal DESC
    """, nativeQuery = true)
    List<Map<String,Object>> topClientes();

    @Query(value = """
    SELECT
    CONCAT(m.nombres,' ',m.apellidos) mecanico,
    e.nombre_especialidad especialidad,
    COUNT(DISTINCT o.id_orden) ordenesAtendidas,
    SUM(d.precio_aplicado*d.cantidad) ingresosGenerados,
    AVG(d.precio_aplicado*d.cantidad) ingresoPromedio,
    AVG(DATEDIFF(o.fecha_real_entrega,o.fecha_ingreso)) diasPromedioEntrega
    FROM mecanico m
    INNER JOIN especialidad e ON m.id_especialidad=e.id_especialidad
    INNER JOIN orden_trabajo o ON m.id_mecanico=o.id_mecanico
    INNER JOIN detalle_orden d ON o.id_orden=d.id_orden
    GROUP BY m.id_mecanico
    ORDER BY ingresosGenerados DESC
    """, nativeQuery = true)
    List<Map<String,Object>> rendimientoMecanicos();

    @Query(value = """
    SELECT
        s.nombre_servicio       nombreServicio,
        COUNT(d.id_detalle)     vecesSolicitado,
        SUM(d.precio_aplicado * d.cantidad) ingresosGenerados
    FROM servicio s
    INNER JOIN detalle_orden d ON s.id_servicio = d.id_servicio
    GROUP BY s.id_servicio, s.nombre_servicio
    ORDER BY ingresosGenerados DESC
    """, nativeQuery = true)
    List<Map<String, Object>> topServicios();
}