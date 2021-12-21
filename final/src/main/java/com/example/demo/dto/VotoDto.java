package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class VotoDto {

    private Long usuarioId;
    private Long emprendimientoId;
    private LocalDateTime fechaCreacion;
    private boolean votaod;

}
