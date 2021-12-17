package com.example.demo.dto;

import com.example.demo.entity.Tipo;
import com.example.demo.entity.Voto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class UsuarioDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Date fechaDeCreacion;
    private String city;
    private String province;
    private String country;
    private Tipo tipo;
    private List<Voto> votos;
}
