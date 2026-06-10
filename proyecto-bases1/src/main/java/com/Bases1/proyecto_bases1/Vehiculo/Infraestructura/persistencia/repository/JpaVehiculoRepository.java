package com.Bases1.proyecto_bases1.Vehiculo.Infraestructura.persistencia.repository;


import com.Bases1.proyecto_bases1.Vehiculo.Infraestructura.persistencia.entity.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface JpaVehiculoRepository
        extends JpaRepository<VehiculoEntity, Long> {

    Optional<VehiculoEntity> findByPlaca(String placa);

    void deleteByPlaca(String placa);

    /*
     * LISTADO PARA EL FRONTEND
     */
    @Query(value = """
            SELECT
                v.placa,
                CONCAT(c.nombres,' ',c.apellidos) propietario,
                m.nombre_marca marca,
                v.modelo,
                co.nombre_color color,
                v.cilindraje,
                tc.nombre_combustible combustible,
                v.kilometraje_actual
            FROM vehiculo v
            INNER JOIN cliente c
                ON v.id_cliente = c.id_cliente
            INNER JOIN marca m
                ON v.id_marca = m.id_marca
            INNER JOIN color co
                ON v.id_color = co.id_color
            INNER JOIN tipo_combustible tc
                ON v.id_tipo_combustible = tc.id_tipo_combustible
            ORDER BY v.placa
            """,
            nativeQuery = true)
    List<Map<String,Object>> listarVehiculos();

    /*
     * HISTORIAL POR PLACA
     */
    @Query(value = """
            SELECT
                v.placa,
                CONCAT(c.nombres,' ',c.apellidos) propietario,
                o.id_orden,
                o.fecha_ingreso,
                o.fecha_real_entrega,
                eo.nombre_estado estado,
                
                CASE
                    WHEN d.id_servicio IS NOT NULL
                    THEN 'Servicio'
                    ELSE 'Repuesto'
                END tipo,
                
                COALESCE(
                    s.nombre_servicio,
                    r.nombre
                ) item,
                
                d.cantidad,
                d.precio_aplicado,
                (d.cantidad * d.precio_aplicado) subtotal
                
            FROM vehiculo v
            INNER JOIN cliente c
                ON v.id_cliente = c.id_cliente
                
            INNER JOIN orden_trabajo o
                ON v.id_vehiculo = o.id_vehiculo
                
            INNER JOIN estado_orden eo
                ON o.id_estado_orden = eo.id_estado_orden
                
            INNER JOIN detalle_orden d
                ON o.id_orden = d.id_orden
                
            LEFT JOIN servicio s
                ON d.id_servicio = s.id_servicio
                
            LEFT JOIN repuesto r
                ON d.id_repuesto = r.id_repuesto
                
            WHERE v.placa = :placa
            
            ORDER BY o.fecha_ingreso DESC
            """,
            nativeQuery = true)
    List<Map<String,Object>> historialVehiculo(String placa);
}