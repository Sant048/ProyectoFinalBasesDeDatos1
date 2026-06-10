package com.Bases1.proyecto_bases1.reporte.application.dto;

public record TopServicioResponse(
        String nombreServicio,
        Long vecesSolicitado,
        Double ingresosGenerados
) {}