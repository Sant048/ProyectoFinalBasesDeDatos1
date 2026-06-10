package com.Bases1.proyecto_bases1.proveedor.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorResponse {

    private Long idProveedor;
    private String razonSocial;
    private String nit;
    private String ciudad;
    private String telefono;
    private String correo;
    private Integer tiempoEntregaDias;
    private Long totalRepuestos;
}