package com.Bases1.proyecto_bases1.vehiculos.domain.model;

import com.Bases1.proyecto_bases1.cliente.domain.model.Cliente;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "VEHICULO")
public class Vehiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVehiculo;

    @Column(name = "placa", nullable = false, unique = true)
    private String placa;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "cilindraje", nullable = false)
    private Integer cilindraje;

    @Column(name = "kilometraje_actual", nullable = false)
    private Integer kilometrajeActual;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private com.taller.marca.domain.model.Marca marca;

    @ManyToOne
    @JoinColumn(name = "id_color", nullable = false)
    private com.taller.color.domain.model.Color color;

    @ManyToOne
    @JoinColumn(name = "id_tipo_combustible", nullable = false)
    private com.taller.combustible.domain.model.TipoCombustible tipoCombustible;

    // Getters y Setters
}
