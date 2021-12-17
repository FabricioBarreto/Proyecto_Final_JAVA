package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
@Entity
@Table(name = "eventos")
@Getter @Setter
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detalle;
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date fechaDeCreacion;
    @JsonFormat(pattern = "dd-MM-yyyy")   
    private Date fechaDeCierre;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private EstadoEvento estado;
    private Long premio;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "evento_id",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "emprendimiento_id"))
    private List<Evento> suscriptores = new ArrayList<>();
}
