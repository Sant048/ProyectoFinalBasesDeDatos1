package com.Bases1.proyecto_bases1.cliente.application.usecase;

import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteRequest;
import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteResponse;

public interface ActualizarClienteUseCase {

    ClienteResponse ejecutar(
            Integer id,
            ClienteRequest request);
}