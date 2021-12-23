package com.example.demo.controller;


import com.example.demo.entity.*;
import com.example.demo.repository.EmprendimientoRepository;
import com.example.demo.repository.EventoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.VotoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoRepository eventoRepository;
    private final VotoRepository votoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmprendimientoRepository emprendimientoRepository;

    public EventoController(EventoRepository eventoRepository, VotoRepository votoRepository, UsuarioRepository usuarioRepository, EmprendimientoRepository emprendimientoRepository) {
        this.eventoRepository = eventoRepository;
        this.votoRepository = votoRepository;
        this.usuarioRepository = usuarioRepository;
        this.emprendimientoRepository = emprendimientoRepository;
    }

    @PostMapping(value = "/{creadorId}")
    public ResponseEntity<?> crearEvento(@PathVariable("creadorId") Long creadorId,
                                         @RequestBody @Valid Evento evento){
        Usuario usuario = usuarioRepository
            .findById(creadorId)
            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        //evento.setFechaDeCierre(LocalDateTime.parse());
        usuario.getEventos().add(evento);
        evento.setCreador(usuario);
        return new ResponseEntity<>(eventoRepository.save(evento), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> obtenerEventos(){
        return new ResponseEntity<>(eventoRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping (value = "/{id}")
    public  void modificarEvento(@PathVariable("id") Long id,
                                             @RequestBody @Valid Evento modificacionDelEvento){
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Evento no encontrado."));
        evento.setDetalle(modificacionDelEvento.getDetalle());
        evento.setFechaDeCierre(modificacionDelEvento.getFechaDeCierre());
        evento.setEstado(modificacionDelEvento.getEstado());
        evento.setPremio(modificacionDelEvento.getPremio());
        eventoRepository.save(evento);
    }

    @DeleteMapping (value = "/{id}")
    public void eliminarEvento(@PathVariable ("id") Long eventoId){
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(()-> new EntityNotFoundException("Evento no encontrado"));
        eventoRepository.delete(evento);
    }

    @PostMapping("suscribirse/{eventoId}/{empId}")
    public ResponseEntity<?> suscribirse(@PathVariable("eventoId") Long eventoId,
                                         @PathVariable("empId") Long empId){
        Emprendimiento emprendimiento = emprendimientoRepository.findById(empId)
                .orElseThrow(()-> new EntityNotFoundException("Emprendimiento no encontrado"));
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(()-> new EntityNotFoundException("Evento no encontrado"));
        evento.agregarSubscriptor(emprendimiento);
        if(evento.getEstado() == EventoEnum.FINALIZADO){
            System.out.println("El evento ha finalizado");
        }
        if(evento.getEstado() == EventoEnum.EN_CURSO){
            System.out.println("El evento ya esta en curso");
        }
        return new ResponseEntity<>(eventoRepository.save(evento), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/ranking")
    public ResponseEntity<?> ranking(@PathVariable Long id) {
        return new ResponseEntity<>(eventoRepository.findById(id), HttpStatus.OK);
    }
}