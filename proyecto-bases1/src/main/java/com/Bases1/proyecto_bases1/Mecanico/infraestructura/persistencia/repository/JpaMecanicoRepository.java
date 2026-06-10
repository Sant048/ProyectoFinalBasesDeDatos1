package com.Bases1.proyecto_bases1.Mecanico.infraestructura.persistencia.repository;


import com.Bases1.proyecto_bases1.Mecanico.infraestructura.persistencia.entidad.MecanicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface JpaMecanicoRepository
        extends JpaRepository<MecanicoEntity, Long> {

    @Query(value = """
        SELECT
            m.id_mecanico,
            m.nombres,
            m.apellidos,
            di.sigla,
            m.numero_documento,
            e.nombre_especialidad,
            m.telefono,
            m.fecha_ingreso,
            em.nombre_estado
        FROM mecanico m
        INNER JOIN documento_identidad di
            ON m.id_tipo_documento=di.id_tipo_documento
        INNER JOIN especialidad e
            ON m.id_especialidad=e.id_especialidad
        INNER JOIN estado_mecanico em
            ON m.id_estado_mecanico=em.id_estado_mecanico
        ORDER BY m.nombres
        """,
            nativeQuery = true)
    List<Map<String,Object>> listar();
}