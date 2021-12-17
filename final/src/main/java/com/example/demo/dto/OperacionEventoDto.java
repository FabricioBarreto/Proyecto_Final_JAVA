package com.example.demo.dto;

import com.example.demo.entity.Emprendimiento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter @Setter
public class OperacionEventoDto {

    @NotNull
    @NotEmpty(message = "El descripcion no puede ser vacio")
    private String detalles;
    @NotNull
    private LocalDate fechaDeCreacion;
    @NotNull
    private LocalDate fechaDeCierre;
    @NotNull
    private boolean estado;
    @NotNull
    private List<Emprendimiento> suscriptores;

}
