package com.Bases1.proyecto_bases1.Vehiculo.aplicacion.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearVehiculoRequest {

    private Long idCliente;
    private String placa;
    private Long idMarca;
    private String modelo;
    private Long idColor;
    private Integer cilindraje;
    private Long idTipoCombustible;
    private Integer kilometrajeActual;
}