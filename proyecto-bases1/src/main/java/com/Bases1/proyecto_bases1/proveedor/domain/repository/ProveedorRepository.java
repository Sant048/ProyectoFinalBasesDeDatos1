package com.Bases1.proyecto_bases1.proveedor.domain.repository;

import com.Bases1.proyecto_bases1.proveedor.application.dto.ProveedorResponse;
import com.Bases1.proyecto_bases1.proveedor.domain.model.Proveedor;

import java.util.List;

public interface ProveedorRepository {

    Proveedor guardar(Proveedor proveedor);

    List<ProveedorResponse> listar();

    void eliminarPorNit(String nit);
}