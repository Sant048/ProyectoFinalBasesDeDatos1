package com.Bases1.proyecto_bases1.ordenTrabajo.domain.repository;

import com.Bases1.proyecto_bases1.ordenTrabajo.application.dto.OrdenTopMecanicoResponse;
import com.Bases1.proyecto_bases1.ordenTrabajo.application.dto.OrdenTrabajoResponse;
import com.Bases1.proyecto_bases1.ordenTrabajo.domain.model.OrdenTrabajo;

import java.util.List;

public interface OrdenTrabajoRepository {

    OrdenTrabajo guardar(OrdenTrabajo orden);

    List<OrdenTrabajoResponse> listar();

    List<OrdenTrabajoResponse> abiertas();

    List<OrdenTrabajoResponse> cerradas();

    OrdenTopMecanicoResponse topMecanico();
}