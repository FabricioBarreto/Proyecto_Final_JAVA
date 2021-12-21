package com.example.demo.dto;

import com.example.demo.entity.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class EmprendimientoDto {

    private String nombre;
    private String descripcion;
    private String contenido;
    private double objetivo;
    private boolean publicadp;
    private List<Tag> tags = new ArrayList<>();
}
