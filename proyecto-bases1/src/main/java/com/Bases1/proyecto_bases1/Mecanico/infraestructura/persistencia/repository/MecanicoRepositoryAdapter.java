package com.Bases1.proyecto_bases1.Mecanico.infraestructura.persistencia.repository;

import com.Bases1.proyecto_bases1.Mecanico.aplicacion.dto.MecanicoResponse;
import com.Bases1.proyecto_bases1.Mecanico.dominio.model.Mecanico;
import com.Bases1.proyecto_bases1.Mecanico.dominio.respository.MecanicoRepository;
import com.Bases1.proyecto_bases1.Mecanico.infraestructura.persistencia.mapper.MecanicoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MecanicoRepositoryAdapter implements MecanicoRepository {

    private final JpaMecanicoRepository jpaRepository;

    @Override
    public Mecanico guardar(Mecanico mecanico) {

        return MecanicoMapper.toDomain(
                jpaRepository.save(
                        MecanicoMapper.toEntity(mecanico)
                )
        );
    }

    @Override
    public List<MecanicoResponse> listar() {

        return jpaRepository.listar()
                .stream()
                .map(this::mapResponse)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);
    }

    private MecanicoResponse mapResponse(
            Map<String, Object> row) {

        return new MecanicoResponse(
                ((Number) row.get("id_mecanico")).longValue(),
                (String) row.get("nombres"),
                (String) row.get("apellidos"),
                (String) row.get("sigla"),
                (String) row.get("numero_documento"),
                (String) row.get("nombre_especialidad"),
                (String) row.get("telefono"),
                (LocalDate) row.get("fecha_ingreso"),
                (String) row.get("nombre_estado")
        );
    }
}