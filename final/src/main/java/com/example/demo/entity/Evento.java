package com.example.demo.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter @Setter
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private EventoEnum estado = EventoEnum.INSCRIBIENDO;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"description","content","created","goal","published","tags" })
    @OrderBy("cantidadDeVotos DESC")
    private List<Emprendimiento> emprendimientosSubscriptos = new ArrayList<>();

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario creador;

    public void agregarSubscriptor(Emprendimiento subscriptor){
        emprendimientosSubscriptos.add(subscriptor);
        subscriptor.getEventos().add(this);
    }
}
