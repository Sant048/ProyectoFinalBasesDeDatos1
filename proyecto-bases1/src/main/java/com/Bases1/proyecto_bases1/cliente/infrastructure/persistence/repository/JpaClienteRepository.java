package com.Bases1.proyecto_bases1.cliente.infrastructure.persistence.repository;


import com.Bases1.proyecto_bases1.cliente.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaClienteRepository
        extends JpaRepository<ClienteEntity, Long> {

    /*
     * LEFT JOIN
     * Entidades sin relación
     * Clientes que no poseen vehículo
     */
    @Query(value = """
        SELECT c.*
        FROM CLIENTE c
        LEFT JOIN VEHICULO v
               ON c.id_cliente = v.id_cliente
        WHERE v.id_vehiculo IS NULL
        """, nativeQuery = true)
    List<ClienteEntity> clientesSinVehiculo();


    /*
     * SUBCONSULTA
     * Cliente con mayor actividad
     */
    @Query(value = """
        SELECT c.*
        FROM CLIENTE c
        WHERE c.id_cliente =
        (
            SELECT v.id_cliente
            FROM VEHICULO v
            INNER JOIN ORDEN_TRABAJO o
                    ON v.id_vehiculo = o.id_vehiculo
            GROUP BY v.id_cliente
            ORDER BY COUNT(o.id_orden) DESC
            LIMIT 1
        )
        """, nativeQuery = true)
    ClienteEntity clienteMasActivo();
}