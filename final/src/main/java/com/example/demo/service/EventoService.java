package com.example.demo.service;
import com.example.demo.entity.*;
import com.example.demo.repository.EmprendimientoRepository;
import com.example.demo.repository.EventoRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Configuration
@EnableScheduling
public class EventoService {

    @Autowired
    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmprendimientoRepository emprendimientoRepository;

    public EventoService(EventoRepository eventoRepository, UsuarioRepository usuarioRepository, EmprendimientoRepository emprendimientoRepository) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
        this.emprendimientoRepository = emprendimientoRepository;
    }


    public ResponseEntity<?> crearEvento(Long id) {

        Evento evento = new Evento();

        evento.setNombre(evento.getNombre());
        evento.setDetalle(evento.getDetalle());
        evento.setFechaDeCreacion(LocalDate.now());
        evento.setFechaDeCierre(evento.getFechaDeCierre());
        evento.setCierreDeRegistro(evento.getCierreDeRegistro());
        evento.setPremio(evento.getPremio());

        evento.setCreador(usuarioRepository.getById(id));
        return new ResponseEntity<>(eventoRepository.save(evento), HttpStatus.CREATED);
    }

    public Evento actualizarEvento(Long id){
        Evento evento = eventoRepository.getById(id);
        evento.setNombre(evento.getNombre());
        evento.setDetalle(evento.getDetalle());
        evento.setPremio(evento.getPremio());
        evento.setFechaDeCierre(evento.getFechaDeCierre());
        evento.setEstado(evento.getEstado());
        evento.setCierreDeRegistro(evento.getCierreDeRegistro());
        return eventoRepository.save(evento);
    }
}
