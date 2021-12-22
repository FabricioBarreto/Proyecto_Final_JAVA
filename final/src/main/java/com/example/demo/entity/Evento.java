package com.example.demo.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter @Setter
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String detalle;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime fechaDeCreacion;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime CierreDeRegistro;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime fechaDeCierre;

    private BigDecimal premio;

    @Enumerated(value = EnumType.STRING)
    private EventoEnum estado = EventoEnum.ABIERTO;

    private boolean activo= false;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @OrderBy("cantidadDeVotos DESC")
    private List<com.example.demo.entity.Emprendimiento> emprendimientosSubscriptos = new ArrayList<>();

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario creador;

    public void agregarSubscriptor(com.example.demo.entity.Emprendimiento subscriptor){
        emprendimientosSubscriptos.add(subscriptor);
        subscriptor.getEventos().add(this);
    }
}
