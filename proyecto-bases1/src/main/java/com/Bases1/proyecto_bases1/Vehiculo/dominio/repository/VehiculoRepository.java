package com.Bases1.proyecto_bases1.Vehiculo.dominio.repository;

import com.Bases1.proyecto_bases1.Vehiculo.aplicacion.dto.HistorialVehiculoResponse;
import com.Bases1.proyecto_bases1.Vehiculo.aplicacion.dto.VehiculoResponse;
import com.Bases1.proyecto_bases1.Vehiculo.dominio.modelo.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepository {

    Vehiculo guardar(Vehiculo vehiculo);

    Optional<Vehiculo> buscarPorId(Long id);

    Optional<Vehiculo> buscarPorPlaca(String placa);

    List<Vehiculo> listarTodos();

    void eliminarPorPlaca(String placa);

    List<VehiculoResponse> listarVehiculos();

    List<HistorialVehiculoResponse> historialVehiculo(
            String placa);
}