package com.Bases1.proyecto_bases1.repuesto.application.dto;

import lombok.Data;

@Data
public class CrearRepuestoRequest {

    private Long idProveedor;
    private String nombre;
    private String referencia;
    private Long idMarcaRepuesto;
    private Double precioUnitario;
    private Integer stockActual;
    private Integer stockMinimo;
}