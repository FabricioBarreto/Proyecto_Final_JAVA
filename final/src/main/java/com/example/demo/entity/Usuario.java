package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "usuarios")
@Getter @Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotEmpty(message = "El campo nombre no puede estar vacio")
    private String nombre;

    @NotEmpty(message = "El campo nombre no puede estar vacio")
    private String apellido;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "fechaCreacion")
    @NotNull
    private LocalDate fechaCreacion;

    @NotEmpty(message = "El campo nombre no puede estar vacio")
    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    @NotEmpty(message = "El campo nombre no puede estar vacio")
    @Size(min = 8, max = 20)
    private String password;

    @NotEmpty(message = "El campo nombre no puede estar vacio")
    private String ciudad;

    @NotEmpty(message = "El campo nombre no puede estar vacio")
    private String provincia;

    @NotEmpty(message = "El campo pais no puede estar vacio")
    private String país;
    
    @Enumerated(value = EnumType.STRING)
    private Tipo tipo;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Emprendimiento> emprendimientos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "votante", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Voto> votos = new ArrayList<>();

}
