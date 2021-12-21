package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter @Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    @NotEmpty(message = "El campo username no puede estar vacio")
    private String email;

    @NotEmpty(message = "El campo password no puede estar vacio")
    @Getter(onMethod = @__(@JsonIgnore))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private UsuarioEnum usuarioEnum;
    private boolean activo = true;

    @NotEmpty(message = "El campo nombre no puede estar vacio")
    private String nombre;

    @NotEmpty(message = "El campo nombre no puede estar vacio")
    private String apellido;

    private String provincia;

    private String ciudad;

    private String pais;

    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaCreacion;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
    public List<Emprendimiento> emprendimientos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creador", cascade = CascadeType.ALL)
    public List<Evento> eventos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "votante", cascade = CascadeType.ALL)
    public List<Voto> votos = new ArrayList<>();
}
