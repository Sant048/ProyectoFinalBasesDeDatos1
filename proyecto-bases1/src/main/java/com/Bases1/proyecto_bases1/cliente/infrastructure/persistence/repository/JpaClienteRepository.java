package com.Bases1.proyecto_bases1.cliente.infrastructure.persistence.repository;


import com.Bases1.proyecto_bases1.cliente.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface JpaClienteRepository
        extends JpaRepository<ClienteEntity, Long> {

    /*
     CONSULTA #1
     */
    @Query(value = """
            SELECT
                c.id_cliente,
                c.nombres,
                c.apellidos,
                di.sigla,
                c.numero_documento,
                c.telefono,
                c.correo,
                c.direccion,
                c.fecha_registro
            FROM cliente c
            INNER JOIN documento_identidad di
                ON c.id_tipo_documento = di.id_tipo_documento
            ORDER BY c.apellidos,c.nombres
            """,
            nativeQuery = true)
    List<Map<String,Object>> listarClientesConDocumento();

    /*
     CONSULTA #2
     */
    @Query(value = """
            SELECT
                c.id_cliente,
                c.nombres,
                c.apellidos,
                di.sigla,
                c.numero_documento,
                c.telefono,
                c.correo
            FROM cliente c
            INNER JOIN documento_identidad di
                ON c.id_tipo_documento = di.id_tipo_documento
            WHERE c.numero_documento = :documento
            """,
            nativeQuery = true)
    Map<String,Object> buscarPorDocumento(String documento);

    /*
     CONSULTA #3
     */
    @Query(value = """
            SELECT
                c.id_cliente,
                c.nombres,
                c.apellidos,
                c.telefono,
                c.correo
            FROM cliente c
            WHERE c.id_cliente IN
            (
                SELECT DISTINCT id_cliente
                FROM vehiculo
            )
            """,
            nativeQuery = true)
    List<Map<String,Object>> clientesConVehiculos();

    /*
     CONSULTA #4
     */
    @Query(value = """
            SELECT
                c.id_cliente,
                c.nombres,
                c.apellidos,
                c.telefono
            FROM cliente c
            LEFT JOIN vehiculo v
                ON c.id_cliente=v.id_cliente
            WHERE v.id_vehiculo IS NULL
            """,
            nativeQuery = true)
    List<Map<String,Object>> clientesSinVehiculos();

    /*
     CONSULTA #5
     */
    @Query(value = """
            SELECT
                c.id_cliente,
                c.nombres,
                c.apellidos,
                COUNT(v.id_vehiculo) total_vehiculos
            FROM cliente c
            LEFT JOIN vehiculo v
                ON c.id_cliente=v.id_cliente
            GROUP BY c.id_cliente,c.nombres,c.apellidos
            ORDER BY total_vehiculos DESC
            LIMIT 1
            """,
            nativeQuery = true)
    Map<String,Object> clienteConMasVehiculos();
}