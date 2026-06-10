package com.Bases1.proyecto_bases1.ordenTrabajo.application.service;

import com.Bases1.proyecto_bases1.ordenTrabajo.application.dto.*;
import com.Bases1.proyecto_bases1.ordenTrabajo.domain.model.OrdenTrabajo;
import com.Bases1.proyecto_bases1.ordenTrabajo.domain.repository.OrdenTrabajoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenTrabajoService {

    private final OrdenTrabajoRepository repository;

    public OrdenTrabajo guardar(
            CrearOrdenTrabajoRequest request) {

        OrdenTrabajo orden =
                OrdenTrabajo.builder()
                        .idVehiculo(request.getIdVehiculo())
                        .idMecanico(request.getIdMecanico())
                        .fechaIngreso(request.getFechaIngreso())
                        .fechaEstimadaEntrega(request.getFechaEstimadaEntrega())
                        .fechaRealEntrega(request.getFechaRealEntrega())
                        .descripcionFalla(request.getDescripcionFalla())
                        .idEstadoOrden(request.getIdEstadoOrden())
                        .build();

        return repository.guardar(orden);
    }

    public List<OrdenTrabajoResponse> listar() {
        return repository.listar();
    }

    public List<OrdenTrabajoResponse> abiertas() {
        return repository.abiertas();
    }

    public List<OrdenTrabajoResponse> cerradas() {
        return repository.cerradas();
    }

    public OrdenTopMecanicoResponse topMecanico() {
        return repository.topMecanico();
    }
}