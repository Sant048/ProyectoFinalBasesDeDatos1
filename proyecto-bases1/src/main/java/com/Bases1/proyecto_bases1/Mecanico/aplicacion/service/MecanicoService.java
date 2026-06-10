package com.Bases1.proyecto_bases1.Mecanico.aplicacion.service;


import com.Bases1.proyecto_bases1.Mecanico.aplicacion.dto.CrearMecanicoRequest;
import com.Bases1.proyecto_bases1.Mecanico.aplicacion.dto.MecanicoResponse;
import com.Bases1.proyecto_bases1.Mecanico.dominio.model.Mecanico;
import com.Bases1.proyecto_bases1.Mecanico.dominio.respository.MecanicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MecanicoService {

    private final MecanicoRepository repository;

    public Mecanico guardar(CrearMecanicoRequest request) {

        Mecanico mecanico = Mecanico.builder()
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .numeroDocumento(request.getNumeroDocumento())
                .idTipoDocumento(request.getIdTipoDocumento())
                .idEspecialidad(request.getIdEspecialidad())
                .telefono(request.getTelefono())
                .fechaIngreso(request.getFechaIngreso())
                .idEstadoMecanico(1L)
                .build();

        return repository.guardar(mecanico);
    }

    public List<MecanicoResponse> listar() {
        return repository.listar();
    }

    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}