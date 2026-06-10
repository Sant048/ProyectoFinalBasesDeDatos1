package com.Bases1.proyecto_bases1.cliente.application.usecase;

import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteVehiculosResponse;

import java.util.List;

public interface ObtenerClientesSinVehiculosUseCase {

    List<ClienteVehiculosResponse> ejecutar();

}
