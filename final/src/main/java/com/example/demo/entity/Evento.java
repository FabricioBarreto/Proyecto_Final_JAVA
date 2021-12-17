package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
@Entity
@Table(name = "eventos")

public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detalle;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date fechaDeCreacion;

    @JsonFormat(pattern = "dd-MM-yyyy")   
    private Date fechaDeCierre;
    
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private EventoEnum estado;
    
    private String suscriptores;
    private Long premio;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuario;
    
    public Evento() {
    }
    
    public Evento(Long id, String detalle ,Date fechaDeCreacion, Date fechaDeCierre,
            @NotNull EventoEnum estado, String suscriptores, Long premio) {
        this.id = id;
        this.detalle = detalle;
        this.fechaDeCreacion = fechaDeCreacion;
        this.fechaDeCierre = fechaDeCierre;
        this.estado = estado;
        this.suscriptores = suscriptores;
        this.premio = premio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Date getFechaDeCierre() {
        return fechaDeCierre;
    }

    public void setFechaDeCierre(Date fechaDeCierre) {
        this.fechaDeCierre = fechaDeCierre;
    }

    public EventoEnum getEstado() {
        return estado;
    }

    public void setEstado(EventoEnum estado) {
        this.estado = estado;
    }

    public String getSuscriptores() {
        return suscriptores;
    }

    public void setSuscriptores(String suscriptores) {
        this.suscriptores = suscriptores;
    }

    public Long getPremio() {
        return premio;
    }

    public void setPremio(Long premio) {
        this.premio = premio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Evento [id =" + id + "detalle =" + detalle + ", fechaDeCreacionEvento =" + fechaDeCreacion
                + ", fechaDeCierreEvento =" + fechaDeCierre + ", estado =" + estado
                + ", suscriptores =" + suscriptores + ", premio =" + premio + "]";
    }
}
