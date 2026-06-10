package com.Bases1.proyecto_bases1.Mecanico.infraestructura.persistencia.mapper;


import com.Bases1.proyecto_bases1.Mecanico.dominio.model.Mecanico;
import com.Bases1.proyecto_bases1.Mecanico.infraestructura.persistencia.entidad.MecanicoEntity;

public class MecanicoMapper {

    public static MecanicoEntity toEntity(Mecanico mecanico) {

        return MecanicoEntity.builder()
                .idMecanico(mecanico.getIdMecanico())
                .nombres(mecanico.getNombres())
                .apellidos(mecanico.getApellidos())
                .numeroDocumento(mecanico.getNumeroDocumento())
                .idTipoDocumento(mecanico.getIdTipoDocumento())
                .idEspecialidad(mecanico.getIdEspecialidad())
                .telefono(mecanico.getTelefono())
                .fechaIngreso(mecanico.getFechaIngreso())
                .idEstadoMecanico(mecanico.getIdEstadoMecanico())
                .build();
    }

    public static Mecanico toDomain(MecanicoEntity entity) {

        return Mecanico.builder()
                .idMecanico(entity.getIdMecanico())
                .nombres(entity.getNombres())
                .apellidos(entity.getApellidos())
                .numeroDocumento(entity.getNumeroDocumento())
                .idTipoDocumento(entity.getIdTipoDocumento())
                .idEspecialidad(entity.getIdEspecialidad())
                .telefono(entity.getTelefono())
                .fechaIngreso(entity.getFechaIngreso())
                .idEstadoMecanico(entity.getIdEstadoMecanico())
                .build();
    }
}