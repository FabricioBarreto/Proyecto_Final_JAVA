package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@RequiredArgsConstructor
@Table(name = "emprendimientos_tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagNombre;

    @ManyToMany(mappedBy = "tags")
    private List<Emprendimiento> emprendimientos = new ArrayList<>();

    @Override
    public String toString() {
        return "Tags{" +
                "tagNOmbre= #'" + tagNombre + '\'' +
                '}';
    }

    public String getTagNombre() {
        return tagNombre;
    }

    public void setTagNombre(String tagNombre) {
        this.tagNombre = tagNombre;
    }
}