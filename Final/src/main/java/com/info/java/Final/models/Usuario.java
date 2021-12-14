package com.info.java.Final.models;

import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@ToString @EqualsAndHashCode
public class Usuario<Interger> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long id;

    //@NotEmpty(message= "El apellido no puede estar vacio")
    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "apellido")
    //@NotEmpty(message= "El apellido no puede estar vacio")
    private String apellido;

    //@NoEmpty(message= "El apellido no puede estar vacio")
    @Getter @Setter @Column(name = "email")
    private String email;

    //@NoEmpty(message= "El apellido no puede estar vacio")
    @Getter @Setter @Column(name = "password")
    private String password;

    @NotNull
    @CreationTimestamp
    @Getter @Setter @Column(name = "fechaDeCreacion")
    private LocalDateTime fechaDeCreacion;

    @Getter @Setter @Column(name = "ciudad")
    private String ciudad;

    @Getter @Setter @Column(name = "provincia")
    private String provincia;

    @Getter @Setter @Column(name = "pais")
    private String pais;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Interger tipoUsuario;


}
