package com.example.demo.controller;

import com.example.demo.entity.Evento;
import com.example.demo.repository.EventoRepository;
import com.example.demo.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private final EventoService eventoService;
    private final EventoRepository eventoRepository;

    public EventoController(EventoService eventoService, EventoRepository eventoRepository) {
        this.eventoService = eventoService;
        this.eventoRepository = eventoRepository;
    }

    @PostMapping
    public ResponseEntity<?> crearEvento(@PathVariable("id") Long id) {
        return new ResponseEntity<>(eventoService.crearEvento(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> obtenerEventos(){
        return new ResponseEntity<>(eventoRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEvento(@PathVariable Long id){
        return new ResponseEntity<>(eventoService.actualizarEvento(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void removerEvento(@PathVariable Long id){
        try {
            eventoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("No se encuentra el evento a eliminar");
        }
    }

    @GetMapping("/{id}/ranking")
    public ResponseEntity<?> ranking(
            @PathVariable Long id) {
        return new ResponseEntity<>(eventoRepository.findById(id), HttpStatus.OK);
    }
}
