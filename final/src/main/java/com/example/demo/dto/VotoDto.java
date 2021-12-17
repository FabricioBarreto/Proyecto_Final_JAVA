package com.example.demo.dto;

import com.example.demo.entity.Emprendimiento;
import com.example.demo.entity.EstadoEvento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class VotoDto {
    private Long id;
    private Long usuarioId;
    private Long emprendimientoId;
    private LocalDateTime fechaCreacion;
    private EstadoEvento estado;
    private List<Emprendimiento> suscriptores;
}
