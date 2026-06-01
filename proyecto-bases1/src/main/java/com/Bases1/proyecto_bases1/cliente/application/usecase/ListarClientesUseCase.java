package com.Bases1.proyecto_bases1.cliente.application.usecase;

import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteResponse;

import java.util.List;

public interface ListarClientesUseCase {

    List<ClienteResponse> ejecutar();
}