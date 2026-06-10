package com.Bases1.proyecto_bases1.cliente.application.usecase;


import com.Bases1.proyecto_bases1.cliente.application.dto.ClienteDocumentoResponse;

import java.util.List;

public interface ObtenerClientesUseCase {

    List<ClienteDocumentoResponse> ejecutar();

}