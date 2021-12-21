package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@ToString
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
    private LocalDate fechaCreacion;

    private double objetivo;

    private boolean publicado;

    private String url;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario owner;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tag> tags = new ArrayList<>();

    @ManyToMany (mappedBy = "emprendimientosSuscriptos")
    private List<Evento> eventos = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "emprendimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voto> votos = new ArrayList<>();
    private Integer cantidadDeVotos = 0;

    public Long getCreador() {
        return owner.getId();
    }

    public int cantidadDeVotos() {
        return votos.size();
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void agregarTag(Tag tag){
        tags.add(tag);
        tag.getEmprendimientos().add(this);
    }

}
