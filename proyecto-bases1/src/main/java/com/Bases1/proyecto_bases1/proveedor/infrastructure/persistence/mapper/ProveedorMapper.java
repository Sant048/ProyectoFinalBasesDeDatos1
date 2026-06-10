package com.Bases1.proyecto_bases1.proveedor.infrastructure.persistence.mapper;

import com.Bases1.proyecto_bases1.proveedor.domain.model.Proveedor;
import com.Bases1.proyecto_bases1.proveedor.infrastructure.persistence.entity.ProveedorEntity;

public class ProveedorMapper {

    public static Proveedor toDomain(
            ProveedorEntity entity){

        return Proveedor.builder()
                .idProveedor(entity.getIdProveedor())
                .razonSocial(entity.getRazonSocial())
                .nit(entity.getNit())
                .telefono(entity.getTelefono())
                .correo(entity.getCorreo())
                .idCiudad(entity.getIdCiudad())
                .tiempoEntregaDias(entity.getTiempoEntregaDias())
                .build();
    }

    public static ProveedorEntity toEntity(
            Proveedor proveedor){

        return ProveedorEntity.builder()
                .idProveedor(proveedor.getIdProveedor())
                .razonSocial(proveedor.getRazonSocial())
                .nit(proveedor.getNit())
                .telefono(proveedor.getTelefono())
                .correo(proveedor.getCorreo())
                .idCiudad(proveedor.getIdCiudad())
                .tiempoEntregaDias(proveedor.getTiempoEntregaDias())
                .build();
    }
}