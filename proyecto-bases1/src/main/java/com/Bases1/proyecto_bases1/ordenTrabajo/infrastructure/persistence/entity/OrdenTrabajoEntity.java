package com.Bases1.proyecto_bases1.ordenTrabajo.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "ORDEN_TRABAJO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenTrabajoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private Long idOrden;

    @Column(name = "id_vehiculo")
    private Long idVehiculo;

    @Column(name = "id_mecanico")
    private Long idMecanico;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "fecha_estimada_entrega")
    private LocalDate fechaEstimadaEntrega;

    @Column(name = "fecha_real_entrega")
    private LocalDate fechaRealEntrega;

    @Column(name = "descripcion_falla")
    private String descripcionFalla;

    @Column(name = "id_estado_orden")
    private Long idEstadoOrden;
}