package com.Bases1.proyecto_bases1.repuesto.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "REPUESTO")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepuestoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_repuesto")
    private Long idRepuesto;

    @Column(name = "id_proveedor")
    private Long idProveedor;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "referencia")
    private String referencia;

    @Column(name = "id_marca_repuesto")
    private Long idMarcaRepuesto;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    @Column(name = "stock_actual")
    private Integer stockActual;

    @Column(name = "stock_minimo")
    private Integer stockMinimo;
}