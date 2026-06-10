package com.Bases1.proyecto_bases1.ordenTrabajo.infrastructure.persistence.repository;

import com.Bases1.proyecto_bases1.ordenTrabajo.application.dto.OrdenTopMecanicoResponse;
import com.Bases1.proyecto_bases1.ordenTrabajo.application.dto.OrdenTrabajoResponse;
import com.Bases1.proyecto_bases1.ordenTrabajo.domain.model.OrdenTrabajo;
import com.Bases1.proyecto_bases1.ordenTrabajo.domain.repository.OrdenTrabajoRepository;
import com.Bases1.proyecto_bases1.ordenTrabajo.infrastructure.persistence.mapper.OrdenTrabajoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class OrdenTrabajoRepositoryAdapter
        implements OrdenTrabajoRepository {

    private final JpaOrdenTrabajoRepository jpaRepository;

    @Override
    public OrdenTrabajo guardar(OrdenTrabajo orden) {

        return OrdenTrabajoMapper.toDomain(
                jpaRepository.save(
                        OrdenTrabajoMapper.toEntity(orden)
                )
        );
    }

    @Override
    public List<OrdenTrabajoResponse> listar() {

        return jpaRepository.listar()
                .stream()
                .map(this::mapResponse)
                .toList();
    }

    @Override
    public List<OrdenTrabajoResponse> abiertas() {

        return jpaRepository.abiertas()
                .stream()
                .map(this::mapResponse)
                .toList();
    }

    @Override
    public List<OrdenTrabajoResponse> cerradas() {

        return jpaRepository.cerradas()
                .stream()
                .map(this::mapResponse)
                .toList();
    }

    @Override
    public OrdenTopMecanicoResponse topMecanico() {

        Map<String,Object> row =
                jpaRepository.topMecanico();

        return new OrdenTopMecanicoResponse(
                ((Number) row.get("id_mecanico")).longValue(),
                (String) row.get("nombres"),
                (String) row.get("apellidos"),
                ((Number) row.get("total_ordenes")).longValue()
        );
    }

    private OrdenTrabajoResponse mapResponse(
            Map<String,Object> row) {

        LocalDate fechaIngreso =
                row.get("fecha_ingreso") instanceof LocalDate
                        ? (LocalDate) row.get("fecha_ingreso")
                        : ((java.sql.Date) row.get("fecha_ingreso")).toLocalDate();

        LocalDate fechaEstimadaEntrega =
                row.get("fecha_estimada_entrega") instanceof LocalDate
                        ? (LocalDate) row.get("fecha_estimada_entrega")
                        : ((java.sql.Date) row.get("fecha_estimada_entrega")).toLocalDate();

        return new OrdenTrabajoResponse(
                ((Number) row.get("id_orden")).longValue(),
                (String) row.get("placa"),
                (String) row.get("cliente"),
                (String) row.get("mecanico"),
                fechaIngreso,
                fechaEstimadaEntrega,
                (String) row.get("descripcion_falla"),
                (String) row.get("estado")
        );
    }
}