package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String nombre;


    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<Emprendimiento> emprendimientos = new ArrayList<>();


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

    public List<Emprendimiento> getEmprendimientos() {
        return emprendimientos;
    }

}
