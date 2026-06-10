package com.Bases1.proyecto_bases1.Vehiculo.aplicacion.dto;

import java.time.LocalDate;

public record HistorialVehiculoResponse(
        String placa,
        String propietario,
        Long idOrden,
        LocalDate fechaIngreso,
        LocalDate fechaRealEntrega,
        String estado,
        String tipo,
        String item,
        Integer cantidad,
        Double precioAplicado,
        Double subtotal
) {
}