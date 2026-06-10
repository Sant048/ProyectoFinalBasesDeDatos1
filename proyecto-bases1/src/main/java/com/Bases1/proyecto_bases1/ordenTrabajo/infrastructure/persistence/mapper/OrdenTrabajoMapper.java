package com.Bases1.proyecto_bases1.ordenTrabajo.infrastructure.persistence.mapper;

import com.Bases1.proyecto_bases1.ordenTrabajo.domain.model.OrdenTrabajo;
import com.Bases1.proyecto_bases1.ordenTrabajo.infrastructure.persistence.entity.OrdenTrabajoEntity;

public class OrdenTrabajoMapper {

    public static OrdenTrabajo toDomain(
            OrdenTrabajoEntity entity) {

        return OrdenTrabajo.builder()
                .idOrden(entity.getIdOrden())
                .idVehiculo(entity.getIdVehiculo())
                .idMecanico(entity.getIdMecanico())
                .fechaIngreso(entity.getFechaIngreso())
                .fechaEstimadaEntrega(entity.getFechaEstimadaEntrega())
                .fechaRealEntrega(entity.getFechaRealEntrega())
                .descripcionFalla(entity.getDescripcionFalla())
                .idEstadoOrden(entity.getIdEstadoOrden())
                .build();
    }

    public static OrdenTrabajoEntity toEntity(
            OrdenTrabajo orden) {

        return OrdenTrabajoEntity.builder()
                .idOrden(orden.getIdOrden())
                .idVehiculo(orden.getIdVehiculo())
                .idMecanico(orden.getIdMecanico())
                .fechaIngreso(orden.getFechaIngreso())
                .fechaEstimadaEntrega(orden.getFechaEstimadaEntrega())
                .fechaRealEntrega(orden.getFechaRealEntrega())
                .descripcionFalla(orden.getDescripcionFalla())
                .idEstadoOrden(orden.getIdEstadoOrden())
                .build();
    }
}