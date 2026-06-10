package com.Bases1.proyecto_bases1.Vehiculo.aplicacion.dto;

public record VehiculoResponse(

        String placa,
        String propietario,
        String marca,
        String modelo,
        String color,
        Integer cilindraje,
        String combustible,
        Integer kilometrajeActual

) {}
