package com.Bases1.proyecto_bases1.cliente.domain.model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Cliente {

    private Integer idCliente;
    private String nombres;
    private String apellidos;
    private String numeroDocumento;
    private Integer idTipoDocumento;
    private String telefono;
    private String correo;
    private String direccion;
    private LocalDate fechaRegistro;

    public Cliente() {
    }

    public Cliente(
            Integer idCliente,
            String nombres,
            String apellidos,
            String numeroDocumento,
            Integer idTipoDocumento,
            String telefono,
            String correo,
            String direccion,
            LocalDate fechaRegistro) {

        this.idCliente = idCliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.idTipoDocumento = idTipoDocumento;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
    }

}