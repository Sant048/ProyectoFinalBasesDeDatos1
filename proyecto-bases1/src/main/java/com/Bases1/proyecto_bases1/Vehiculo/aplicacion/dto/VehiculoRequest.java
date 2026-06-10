package com.Bases1.proyecto_bases1.Vehiculo.aplicacion.dto;

public record VehiculoRequest(

        Long idCliente,
        String placa,
        Long idMarca,
        String modelo,
        Long idColor,
        Integer cilindraje,
        Long idTipoCombustible,
        Integer kilometrajeActual

) {}