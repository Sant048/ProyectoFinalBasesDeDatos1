package com.Bases1.proyecto_bases1.ordenTrabajo.infrastructure.persistence.repository;

import com.Bases1.proyecto_bases1.ordenTrabajo.infrastructure.persistence.entity.OrdenTrabajoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface JpaOrdenTrabajoRepository
        extends JpaRepository<OrdenTrabajoEntity, Long> {

    @Query(value = """
            SELECT
                o.id_orden,
                v.placa,
                CONCAT(c.nombres,' ',c.apellidos) AS cliente,
                CONCAT(m.nombres,' ',m.apellidos) AS mecanico,
                o.fecha_ingreso,
                o.fecha_estimada_entrega,
                o.descripcion_falla,
                eo.nombre_estado AS estado
            FROM orden_trabajo o
            INNER JOIN vehiculo v
                ON o.id_vehiculo = v.id_vehiculo
            INNER JOIN cliente c
                ON v.id_cliente = c.id_cliente
            INNER JOIN mecanico m
                ON o.id_mecanico = m.id_mecanico
            INNER JOIN estado_orden eo
                ON o.id_estado_orden = eo.id_estado_orden
            ORDER BY o.id_orden;
                    """,
            nativeQuery = true)
    List<Map<String,Object>> listar();

    @Query(value = """
        SELECT
            o.id_orden,
            v.placa,
            v.modelo,
            CONCAT(m.nombres,' ',m.apellidos) mecanico,
            o.fecha_ingreso,
            o.fecha_estimada_entrega,
            o.descripcion_falla,
            eo.nombre_estado estado
        FROM orden_trabajo o
        INNER JOIN vehiculo v
            ON o.id_vehiculo = v.id_vehiculo
        INNER JOIN mecanico m
            ON o.id_mecanico = m.id_mecanico
        INNER JOIN estado_orden eo
            ON o.id_estado_orden = eo.id_estado_orden
        WHERE eo.nombre_estado = 'Abierta'
        ORDER BY o.id_orden
        """,
            nativeQuery = true)
    List<Map<String,Object>> abiertas();

    @Query(value = """
        SELECT
            o.id_orden,
            v.placa,
            v.modelo,
            CONCAT(m.nombres,' ',m.apellidos) mecanico,
            o.fecha_ingreso,
            o.fecha_estimada_entrega,
            o.descripcion_falla,
            eo.nombre_estado estado
        FROM orden_trabajo o
        INNER JOIN vehiculo v
            ON o.id_vehiculo = v.id_vehiculo
        INNER JOIN mecanico m
            ON o.id_mecanico = m.id_mecanico
        INNER JOIN estado_orden eo
            ON o.id_estado_orden = eo.id_estado_orden
        WHERE eo.nombre_estado = 'Cerrada'
        ORDER BY o.id_orden
        """,
            nativeQuery = true)
    List<Map<String,Object>> cerradas();

    @Query(value = """
        SELECT
            m.id_mecanico,
            m.nombres,
            m.apellidos,
            COUNT(o.id_orden) total_ordenes
        FROM mecanico m
        INNER JOIN orden_trabajo o
            ON m.id_mecanico = o.id_mecanico
        GROUP BY
            m.id_mecanico,
            m.nombres,
            m.apellidos
        ORDER BY total_ordenes DESC
        LIMIT 1
        """,
            nativeQuery = true)
    Map<String,Object> topMecanico();
}