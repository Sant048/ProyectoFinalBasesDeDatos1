package com.Bases1.proyecto_bases1.Vehiculo.Infraestructura.persistencia.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "VEHICULO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Long idVehiculo;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "placa")
    private String placa;

    @Column(name = "id_marca")
    private Long idMarca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "id_color")
    private Long idColor;

    @Column(name = "cilindraje")
    private Integer cilindraje;

    @Column(name = "id_tipo_combustible")
    private Long idTipoCombustible;

    @Column(name = "kilometraje_actual")
    private Integer kilometrajeActual;
}