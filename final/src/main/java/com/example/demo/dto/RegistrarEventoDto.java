package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public class RegistrarEventoDto {
    private String nombre;
    private String descripcion;

    @NotNull
    @Positive
    @JsonProperty(value = "id_usuario")
    private Long idUsuario;

    private List<Long> tags;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Long> getTags() {
        return tags;
    }

    public void setTags(List<Long> tags) {
        this.tags = tags;
    }

    public RegistrarEventoDto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
