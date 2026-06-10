package com.Bases1.proyecto_bases1.proveedor.infrastructure.persistence.repository;

import com.Bases1.proyecto_bases1.proveedor.infrastructure.persistence.entity.ProveedorEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface JpaProveedorRepository
        extends JpaRepository<ProveedorEntity,Long> {

    @Query(value = """
        SELECT
            p.id_proveedor,
            p.razon_social,
            p.nit,
            c.nombre_ciudad ciudad,
            p.telefono,
            p.correo,
            p.tiempo_entrega_dias,
            COUNT(r.id_repuesto) total_repuestos
        FROM proveedor p
        INNER JOIN ciudad c
            ON p.id_ciudad=c.id_ciudad
        LEFT JOIN repuesto r
            ON p.id_proveedor=r.id_proveedor
        GROUP BY
            p.id_proveedor,
            p.razon_social,
            p.nit,
            c.nombre_ciudad,
            p.telefono,
            p.correo,
            p.tiempo_entrega_dias
        ORDER BY p.razon_social
        """,
            nativeQuery = true)
    List<Map<String,Object>> listar();

    @Transactional
    void deleteByNit(String nit);
}