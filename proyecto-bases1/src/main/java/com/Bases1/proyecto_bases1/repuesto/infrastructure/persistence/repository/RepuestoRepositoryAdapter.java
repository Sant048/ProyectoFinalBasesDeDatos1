package com.Bases1.proyecto_bases1.repuesto.infrastructure.persistence.repository;

import com.Bases1.proyecto_bases1.repuesto.application.dto.RepuestoResponse;
import com.Bases1.proyecto_bases1.repuesto.domain.model.Repuesto;
import com.Bases1.proyecto_bases1.repuesto.domain.repository.RepuestoRepository;
import com.Bases1.proyecto_bases1.repuesto.infrastructure.persistence.mapper.RepuestoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RepuestoRepositoryAdapter
        implements RepuestoRepository {

    private final JpaRepuestoRepository jpaRepository;

    @Override
    public Repuesto guardar(Repuesto repuesto) {

        return RepuestoMapper.toDomain(
                jpaRepository.save(
                        RepuestoMapper.toEntity(repuesto)
                )
        );
    }

    @Override
    public List<RepuestoResponse> listar() {

        return jpaRepository.listar()
                .stream()
                .map(this::mapResponse)
                .toList();
    }

    @Override
    public List<RepuestoResponse> criticos() {

        return jpaRepository.criticos()
                .stream()
                .map(this::mapResponse)
                .toList();
    }

    @Override
    public void eliminarPorReferencia(String referencia) {
        jpaRepository.deleteByReferencia(referencia);
    }

    private RepuestoResponse mapResponse(
            Map<String,Object> row){

        return new RepuestoResponse(
                ((Number) row.get("id_repuesto")).longValue(),
                (String) row.get("nombre"),
                (String) row.get("referencia"),
                (String) row.get("marcaRepuesto"),
                (String) row.get("proveedor"),
                ((Number) row.get("precio_unitario")).doubleValue(),
                ((Number) row.get("stock_actual")).intValue(),
                ((Number) row.get("stock_minimo")).intValue()
        );
    }
}