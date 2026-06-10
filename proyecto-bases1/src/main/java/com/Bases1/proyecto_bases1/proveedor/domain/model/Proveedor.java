package com.Bases1.proyecto_bases1.proveedor.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    private Long idProveedor;
    private String razonSocial;
    private String nit;
    private String telefono;
    private String correo;
    private Long idCiudad;
    private Integer tiempoEntregaDias;
}