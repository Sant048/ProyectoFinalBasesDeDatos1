package com.Bases1.proyecto_bases1.Mecanico.infraestructura.persistencia.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "MECANICO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MecanicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mecanico")
    private Long idMecanico;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "id_tipo_documento")
    private Long idTipoDocumento;

    @Column(name = "id_especialidad")
    private Long idEspecialidad;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "id_estado_mecanico")
    private Long idEstadoMecanico;
}