package com.Bases1.proyecto_bases1.repuesto.application.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepuestoResponse {

    private Long idRepuesto;
    private String nombre;
    private String referencia;
    private String marcaRepuesto;
    private String proveedor;
    private Double precioUnitario;
    private Integer stockActual;
    private Integer stockMinimo;
}