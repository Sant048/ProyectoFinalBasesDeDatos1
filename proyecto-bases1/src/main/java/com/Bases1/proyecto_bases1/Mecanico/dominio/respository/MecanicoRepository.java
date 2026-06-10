package com.Bases1.proyecto_bases1.Mecanico.dominio.respository;


import com.Bases1.proyecto_bases1.Mecanico.aplicacion.dto.MecanicoResponse;
import com.Bases1.proyecto_bases1.Mecanico.dominio.model.Mecanico;

import java.util.List;

public interface MecanicoRepository {

    Mecanico guardar(Mecanico mecanico);

    List<MecanicoResponse> listar();

    void eliminar(Long id);
}