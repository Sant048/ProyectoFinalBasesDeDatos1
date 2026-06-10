package com.Bases1.proyecto_bases1.repuesto.infrastructure.persistence.mapper;

import com.Bases1.proyecto_bases1.repuesto.domain.model.Repuesto;
import com.Bases1.proyecto_bases1.repuesto.infrastructure.persistence.entity.RepuestoEntity;

public class RepuestoMapper {

    public static Repuesto toDomain(RepuestoEntity entity){

        return Repuesto.builder()
                .idRepuesto(entity.getIdRepuesto())
                .idProveedor(entity.getIdProveedor())
                .nombre(entity.getNombre())
                .referencia(entity.getReferencia())
                .idMarcaRepuesto(entity.getIdMarcaRepuesto())
                .precioUnitario(entity.getPrecioUnitario())
                .stockActual(entity.getStockActual())
                .stockMinimo(entity.getStockMinimo())
                .build();
    }

    public static RepuestoEntity toEntity(Repuesto repuesto){

        return RepuestoEntity.builder()
                .idRepuesto(repuesto.getIdRepuesto())
                .idProveedor(repuesto.getIdProveedor())
                .nombre(repuesto.getNombre())
                .referencia(repuesto.getReferencia())
                .idMarcaRepuesto(repuesto.getIdMarcaRepuesto())
                .precioUnitario(repuesto.getPrecioUnitario())
                .stockActual(repuesto.getStockActual())
                .stockMinimo(repuesto.getStockMinimo())
                .build();
    }
}