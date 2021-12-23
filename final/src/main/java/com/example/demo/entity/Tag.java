package com.example.demo.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagNombre;

    @ManyToMany(mappedBy = "tags")
    private List<Emprendimiento> emprendimientos;

    public void agregarEmprendimiento(Emprendimiento emprendimiento){
        emprendimientos.add(emprendimiento);
        emprendimiento.getTags().add(this);
    }

}