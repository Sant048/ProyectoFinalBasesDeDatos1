package com.Bases1.proyecto_bases1.reporte.application.service;

import com.Bases1.proyecto_bases1.reporte.domain.repository.ReporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final ReporteRepository repository;

    public Object resumen(){
        return repository.resumen();
    }

    public Object estados(){
        return repository.ordenesPorEstado();
    }

    public Object topMecanicos(){
        return repository.topMecanicos();
    }

    public Object ingresosMes(){
        return repository.ingresosPorMes();
    }

    public Object topClientes(){
        return repository.topClientes();
    }

    public Object rendimiento(){
        return repository.rendimientoMecanicos();
    }

    public Object topServicios() {
        return repository.topServicios();
    }


}