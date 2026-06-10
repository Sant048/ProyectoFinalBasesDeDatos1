package com.Bases1.proyecto_bases1.proveedor.application.service;

import com.Bases1.proyecto_bases1.proveedor.application.dto.CrearProveedorRequest;
import com.Bases1.proyecto_bases1.proveedor.application.dto.ProveedorResponse;
import com.Bases1.proyecto_bases1.proveedor.domain.model.Proveedor;
import com.Bases1.proyecto_bases1.proveedor.domain.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorRepository repository;

    public Proveedor guardar(
            CrearProveedorRequest request){

        return repository.guardar(
                Proveedor.builder()
                        .razonSocial(request.getRazonSocial())
                        .nit(request.getNit())
                        .telefono(request.getTelefono())
                        .correo(request.getCorreo())
                        .idCiudad(request.getIdCiudad())
                        .tiempoEntregaDias(request.getTiempoEntregaDias())
                        .build()
        );
    }

    public List<ProveedorResponse> listar(){
        return repository.listar();
    }

    public void eliminar(String nit){
        repository.eliminarPorNit(nit);
    }
}