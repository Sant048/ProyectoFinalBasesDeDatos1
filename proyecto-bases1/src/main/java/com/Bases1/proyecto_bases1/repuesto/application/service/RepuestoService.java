package com.Bases1.proyecto_bases1.repuesto.application.service;

import com.Bases1.proyecto_bases1.repuesto.application.dto.*;
import com.Bases1.proyecto_bases1.repuesto.domain.model.Repuesto;
import com.Bases1.proyecto_bases1.repuesto.domain.repository.RepuestoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepuestoService {

    private final RepuestoRepository repository;

    public Repuesto guardar(CrearRepuestoRequest request){

        return repository.guardar(
                Repuesto.builder()
                        .idProveedor(request.getIdProveedor())
                        .nombre(request.getNombre())
                        .referencia(request.getReferencia())
                        .idMarcaRepuesto(request.getIdMarcaRepuesto())
                        .precioUnitario(request.getPrecioUnitario())
                        .stockActual(request.getStockActual())
                        .stockMinimo(request.getStockMinimo())
                        .build()
        );
    }

    public List<RepuestoResponse> listar(){
        return repository.listar();
    }

    public List<RepuestoResponse> criticos(){
        return repository.criticos();
    }

    public void eliminar(String referencia){
        repository.eliminarPorReferencia(referencia);
    }
}