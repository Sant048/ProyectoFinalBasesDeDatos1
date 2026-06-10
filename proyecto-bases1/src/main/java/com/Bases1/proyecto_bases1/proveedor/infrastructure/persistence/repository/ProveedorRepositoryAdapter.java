package com.Bases1.proyecto_bases1.proveedor.infrastructure.persistence.repository;

import com.Bases1.proyecto_bases1.proveedor.application.dto.ProveedorResponse;
import com.Bases1.proyecto_bases1.proveedor.domain.model.Proveedor;
import com.Bases1.proyecto_bases1.proveedor.domain.repository.ProveedorRepository;
import com.Bases1.proyecto_bases1.proveedor.infrastructure.persistence.mapper.ProveedorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProveedorRepositoryAdapter
        implements ProveedorRepository {

    private final JpaProveedorRepository jpaRepository;

    @Override
    public Proveedor guardar(Proveedor proveedor) {

        return ProveedorMapper.toDomain(
                jpaRepository.save(
                        ProveedorMapper.toEntity(proveedor)
                )
        );
    }

    @Override
    public List<ProveedorResponse> listar() {

        return jpaRepository.listar()
                .stream()
                .map(this::mapResponse)
                .toList();
    }

    @Override
    public void eliminarPorNit(String nit) {
        jpaRepository.deleteByNit(nit);
    }

    private ProveedorResponse mapResponse(
            Map<String,Object> row){

        return new ProveedorResponse(
                ((Number) row.get("id_proveedor")).longValue(),
                (String) row.get("razon_social"),
                (String) row.get("nit"),
                (String) row.get("ciudad"),
                (String) row.get("telefono"),
                (String) row.get("correo"),
                ((Number) row.get("tiempo_entrega_dias")).intValue(),
                ((Number) row.get("total_repuestos")).longValue()
        );
    }
}