package com.example.demo.dto;

import java.time.LocalDateTime;

public class VotoDto {
    private Long usuarioId;
    private Long emprendimientoId;
    private LocalDateTime fechaCreacion;
    private boolean votos;

    public Long getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    public Long getEmprendimientoId() {
        return emprendimientoId;
    }
    public void setEmprendimientoId(Long emprendimientoId) {
        this.emprendimientoId = emprendimientoId;
    }
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public boolean fueVotado() {
        return votos;
    }
    public void setVotos(boolean votos) {
        this.votos = votos;
    }
}
