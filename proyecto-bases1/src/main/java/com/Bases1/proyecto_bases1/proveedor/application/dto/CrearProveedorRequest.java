package com.Bases1.proyecto_bases1.proveedor.application.dto;

import lombok.Data;

@Data
public class CrearProveedorRequest {

    private String razonSocial;
    private String nit;
    private String telefono;
    private String correo;
    private Long idCiudad;
    private Integer tiempoEntregaDias;
}