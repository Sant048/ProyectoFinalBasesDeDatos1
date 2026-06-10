package com.Bases1.proyecto_bases1.Vehiculo.Infraestructura.persistencia.repository;


import com.Bases1.proyecto_bases1.Vehiculo.Infraestructura.persistencia.entity.VehiculoEntity;
import com.Bases1.proyecto_bases1.Vehiculo.Infraestructura.persistencia.mapper.VehiculoMapper;
import com.Bases1.proyecto_bases1.Vehiculo.aplicacion.dto.HistorialVehiculoResponse;
import com.Bases1.proyecto_bases1.Vehiculo.aplicacion.dto.VehiculoResponse;
import com.Bases1.proyecto_bases1.Vehiculo.dominio.modelo.Vehiculo;
import com.Bases1.proyecto_bases1.Vehiculo.dominio.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VehiculoRepositoryAdapter
        implements VehiculoRepository {

    private final JpaVehiculoRepository jpaRepository;

    @Override
    public Vehiculo guardar(Vehiculo vehiculo) {

        VehiculoEntity entity =
                jpaRepository.save(
                        VehiculoMapper.toEntity(vehiculo));

        return VehiculoMapper.toDomain(entity);
    }

    @Override
    public Optional<Vehiculo> buscarPorId(Long id) {

        return jpaRepository.findById(id)
                .map(VehiculoMapper::toDomain);
    }

    @Override
    public Optional<Vehiculo> buscarPorPlaca(String placa) {

        return jpaRepository.findByPlaca(placa)
                .map(VehiculoMapper::toDomain);
    }

    @Override
    public List<Vehiculo> listarTodos() {

        return jpaRepository.findAll()
                .stream()
                .map(VehiculoMapper::toDomain)
                .toList();
    }

    @Override
    public void eliminarPorPlaca(String placa) {
        jpaRepository.deleteByPlaca(placa);
    }

    @Override
    public List<VehiculoResponse> listarVehiculos() {

        return jpaRepository.listarVehiculos()
                .stream()
                .map(this::mapVehiculo)
                .toList();
    }

    @Override
    public List<HistorialVehiculoResponse> historialVehiculo(
            String placa) {

        return jpaRepository.historialVehiculo(placa)
                .stream()
                .map(this::mapHistorial)
                .toList();
    }

    private VehiculoResponse mapVehiculo(
            Map<String,Object> row) {

        return new VehiculoResponse(
                (String) row.get("placa"),
                (String) row.get("propietario"),
                (String) row.get("marca"),
                (String) row.get("modelo"),
                (String) row.get("color"),
                ((Number) row.get("cilindraje")).intValue(),
                (String) row.get("combustible"),
                ((Number) row.get("kilometraje_actual")).intValue()
        );
    }

    private HistorialVehiculoResponse mapHistorial(
            Map<String,Object> row) {

        return new HistorialVehiculoResponse(
                (String) row.get("placa"),
                (String) row.get("propietario"),
                ((Number) row.get("id_orden")).longValue(),

                row.get("fecha_ingreso") != null
                        ? ((Date) row.get("fecha_ingreso")).toLocalDate()
                        : null,

                row.get("fecha_real_entrega") != null
                        ? ((Date) row.get("fecha_real_entrega")).toLocalDate()
                        : null,

                (String) row.get("estado"),
                (String) row.get("tipo"),
                (String) row.get("item"),

                ((Number) row.get("cantidad")).intValue(),

                ((Number) row.get("precio_aplicado")).doubleValue(),

                ((Number) row.get("subtotal")).doubleValue()
        );
    }
}