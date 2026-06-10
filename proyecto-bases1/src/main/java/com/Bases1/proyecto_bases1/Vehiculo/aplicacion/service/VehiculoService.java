package com.Bases1.proyecto_bases1.Vehiculo.aplicacion.service;


import com.Bases1.proyecto_bases1.Vehiculo.aplicacion.dto.CrearVehiculoRequest;
import com.Bases1.proyecto_bases1.Vehiculo.aplicacion.dto.HistorialVehiculoResponse;
import com.Bases1.proyecto_bases1.Vehiculo.aplicacion.dto.VehiculoResponse;
import com.Bases1.proyecto_bases1.Vehiculo.dominio.modelo.Vehiculo;
import com.Bases1.proyecto_bases1.Vehiculo.dominio.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculoService {

    private final VehiculoRepository repository;

    public Vehiculo guardar(
            CrearVehiculoRequest request) {

        Vehiculo vehiculo = Vehiculo.builder()
                .idCliente(request.getIdCliente())
                .placa(request.getPlaca())
                .idMarca(request.getIdMarca())
                .modelo(request.getModelo())
                .idColor(request.getIdColor())
                .cilindraje(request.getCilindraje())
                .idTipoCombustible(
                        request.getIdTipoCombustible())
                .kilometrajeActual(
                        request.getKilometrajeActual())
                .build();

        return repository.guardar(vehiculo);
    }

    public List<VehiculoResponse> listarVehiculos() {
        return repository.listarVehiculos();
    }

    public List<HistorialVehiculoResponse>
    historialVehiculo(String placa) {

        return repository.historialVehiculo(placa);
    }

    public void eliminar(String placa) {
        repository.eliminarPorPlaca(placa);
    }
}