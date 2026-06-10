package com.Bases1.proyecto_bases1.Vehiculo.Infraestructura.persistencia.mapper;


import com.Bases1.proyecto_bases1.Vehiculo.Infraestructura.persistencia.entity.VehiculoEntity;
import com.Bases1.proyecto_bases1.Vehiculo.dominio.modelo.Vehiculo;

public class VehiculoMapper {

    private VehiculoMapper() {
    }

    public static Vehiculo toDomain(
            VehiculoEntity entity) {

        if (entity == null) {
            return null;
        }

        return Vehiculo.builder()
                .idVehiculo(entity.getIdVehiculo())
                .idCliente(entity.getIdCliente())
                .placa(entity.getPlaca())
                .idMarca(entity.getIdMarca())
                .modelo(entity.getModelo())
                .idColor(entity.getIdColor())
                .cilindraje(entity.getCilindraje())
                .idTipoCombustible(entity.getIdTipoCombustible())
                .kilometrajeActual(entity.getKilometrajeActual())
                .build();
    }

    public static VehiculoEntity toEntity(
            Vehiculo vehiculo) {

        if (vehiculo == null) {
            return null;
        }

        return VehiculoEntity.builder()
                .idVehiculo(vehiculo.getIdVehiculo())
                .idCliente(vehiculo.getIdCliente())
                .placa(vehiculo.getPlaca())
                .idMarca(vehiculo.getIdMarca())
                .modelo(vehiculo.getModelo())
                .idColor(vehiculo.getIdColor())
                .cilindraje(vehiculo.getCilindraje())
                .idTipoCombustible(vehiculo.getIdTipoCombustible())
                .kilometrajeActual(vehiculo.getKilometrajeActual())
                .build();
    }
}