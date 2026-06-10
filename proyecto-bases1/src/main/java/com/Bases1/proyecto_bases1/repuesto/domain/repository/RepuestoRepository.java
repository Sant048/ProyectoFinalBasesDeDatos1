package com.Bases1.proyecto_bases1.repuesto.domain.repository;

import com.Bases1.proyecto_bases1.repuesto.application.dto.RepuestoResponse;
import com.Bases1.proyecto_bases1.repuesto.domain.model.Repuesto;

import java.util.List;

public interface RepuestoRepository {

    Repuesto guardar(Repuesto repuesto);

    List<RepuestoResponse> listar();

    List<RepuestoResponse> criticos();

    void eliminarPorReferencia(String referencia);
}