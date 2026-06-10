package com.Bases1.proyecto_bases1.repuesto.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Repuesto {

    private Long idRepuesto;
    private Long idProveedor;
    private String nombre;
    private String referencia;
    private Long idMarcaRepuesto;
    private Double precioUnitario;
    private Integer stockActual;
    private Integer stockMinimo;
}