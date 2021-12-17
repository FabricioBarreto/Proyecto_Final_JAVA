package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "emprendimientos")
@Getter @Setter @ToString
public class Emprendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotEmpty(message = "El campo nombre no puede estar vacio")
    @Size(min = 2, max = 255)
    private String nombre;

    @NotEmpty(message = "El campo descripcion no puede estar vacio")
    @Size(min = 2, max = 255)
    private String descripcion;

    @NotEmpty(message = "El campo contenido no puede estar vacio")
    @Size(min = 2, max = 255)
    private String contenido;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaDeCreacion;

    @Min(value = 0)
    private Long objetivo;

    private Boolean publicado;

    private String url;

    // Relaciones

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario owner;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "emprendimineto_id",
            joinColumns = @JoinColumn(name = "emprendimiento_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "suscriptores")
    private List<Evento> eventos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    private Voto voto;

}
