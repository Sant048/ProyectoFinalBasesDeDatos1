package com.Bases1.proyecto_bases1.repuesto.infrastructure.persistence.repository;

import com.Bases1.proyecto_bases1.repuesto.infrastructure.persistence.entity.RepuestoEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface JpaRepuestoRepository
        extends JpaRepository<RepuestoEntity,Long> {

    @Query(value = """
        SELECT
            r.id_repuesto,
            r.nombre,
            r.referencia,
            mr.nombre_marca marcaRepuesto,
            p.razon_social proveedor,
            r.precio_unitario,
            r.stock_actual,
            r.stock_minimo
        FROM repuesto r
        INNER JOIN marca_repuesto mr
            ON r.id_marca_repuesto=mr.id_marca_repuesto
        INNER JOIN proveedor p
            ON r.id_proveedor=p.id_proveedor
        ORDER BY r.nombre
        """, nativeQuery = true)
    List<Map<String,Object>> listar();

    @Query(value = """
        SELECT
            r.id_repuesto,
            r.nombre,
            r.referencia,
            mr.nombre_marca marcaRepuesto,
            p.razon_social proveedor,
            r.precio_unitario,
            r.stock_actual,
            r.stock_minimo
        FROM repuesto r
        INNER JOIN marca_repuesto mr
            ON r.id_marca_repuesto=mr.id_marca_repuesto
        INNER JOIN proveedor p
            ON r.id_proveedor=p.id_proveedor
        WHERE r.stock_actual <= r.stock_minimo
        """, nativeQuery = true)
    List<Map<String,Object>> criticos();

    @Transactional
    void deleteByReferencia(String referencia);
}