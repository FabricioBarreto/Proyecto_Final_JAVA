package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "emprendimientos")
public class Emprendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String nombre;
    private String descripcion;
    private String contenido;
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaDeCreacion;
    private Long objetivo;
    private Boolean publicado;
    private String url;
    private Boolean activo;
   
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario owner;

    public Emprendimiento() {
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "emprendimiento_id",
            joinColumns = @JoinColumn(name = "emprendimiento_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"))
    private List<Tag> tags = new ArrayList<>();



    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Long getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Long objetivo) {
        this.objetivo = objetivo;
    }

    public Boolean getPublicado() {
        return publicado;
    }

    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {this.activo = activo;}

    public Usuario getUsuario() {
        return owner;
    }

    public void setUsuario(Usuario usuario) {
        this.owner = usuario;
    }

    public void setOwner(Usuario usuario) {
    }

    public void agregarTag(Tag tag){
        tag.add(tag);
        tag.getEmprendimientos().add(this);
    }

    public void removerTag(Tag tag){
        tags.remove(tag);
        tag.getEmprendimientos().remove(null);
    }

    public List<Tag> getTags(){
        return tags;
    }
    public void setTags(List tags) {
        this.tags = tags;
    }
}
