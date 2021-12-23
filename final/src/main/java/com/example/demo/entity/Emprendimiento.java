package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter @Setter
public class Emprendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String contenido;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime fechaCreacion;

    private double objetivo;

    private boolean publicado;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario owner;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tag> tags = new ArrayList<>();

    @ManyToMany (mappedBy = "emprendimientosSubscriptos")
    private List<Evento> eventos = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "emprendimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voto> votos = new ArrayList<>();
    private Integer cantidadDeVotos = 0;

    public void setVoto(Voto voto) {
        votos.add(voto);
        voto.setEmprendimiento(this);
    }

    public void agregarTag(Tag tag){
        tags.add(tag);
        tag.getEmprendimientos().add(this);
    }

    public void suscrinirseEvennto(Evento evento){
        if(this.eventos == null){
            this.eventos = new ArrayList<>();
        }
        this.eventos.add(evento);
    }

}
