package com.Bases1.proyecto_bases1.Vehiculo.dominio.modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Vehiculo {

    private Long idVehiculo;
    private Long idCliente;

    private String placa;

    private Long idMarca;

    private String modelo;

    private Long idColor;

    private Integer cilindraje;

    private Long idTipoCombustible;

    private Integer kilometrajeActual;

    public Vehiculo() {
    }

    public Vehiculo(
            Long idVehiculo,
            Long idCliente,
            String placa,
            Long idMarca,
            String modelo,
            Long idColor,
            Integer cilindraje,
            Long idTipoCombustible,
            Integer kilometrajeActual) {

        this.idVehiculo = idVehiculo;
        this.idCliente = idCliente;
        this.placa = placa;
        this.idMarca = idMarca;
        this.modelo = modelo;
        this.idColor = idColor;
        this.cilindraje = cilindraje;
        this.idTipoCombustible = idTipoCombustible;
        this.kilometrajeActual = kilometrajeActual;
    }
}