package com.example.demo.controller;

import com.example.demo.entity.Emprendimiento;
import com.example.demo.entity.Evento;
import com.example.demo.repository.EventoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoRepository eventoRepository;

    public EventoController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @PostMapping
    public ResponseEntity<?> crearEvento(@RequestBody @Valid Evento evento){
        return new ResponseEntity<>(eventoRepository.save(evento), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> obtenerEventos(){
        return new ResponseEntity<>(eventoRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<?> modificarEvento(@PathVariable("id") Long id,
                                             @RequestBody @Valid Evento modificacionDelEvento){
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Evento no encontrado."));
        evento.setDetalle(modificacionDelEvento.getDetalle());
        evento.setFechaDeCierre(modificacionDelEvento.getFechaDeCierre());
        evento.setEstado(modificacionDelEvento.getEstado());
        evento.setPremio(modificacionDelEvento.getPremio());
        return new ResponseEntity<>(eventoRepository.save(evento), HttpStatus.OK);
    }

    @DeleteMapping (value = "/{id}")
    public void eliminarEvento(@PathVariable ("id") Long eventoId){
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(()-> new EntityNotFoundException("Evento no encontrado"));
        evento.setActivo(false);
    }

    @GetMapping("/ranking/{id}")
    public List<Emprendimiento>  ranking(@RequestParam(name = "id", required = true)Long id){
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento no encontrado"));
        List<Emprendimiento> emprendimientos = evento.getEmprendimientosSubscriptos();
        Comparator<Emprendimiento> comparador = (Emprendimiento e1, Emprendimiento e2) ->
                Integer.compare(e1.getVotos().size(), (e2.getVotos().size()));
        List<Emprendimiento> emprenimientosOrdenados = emprendimientos.stream()
                .sorted(comparador)
                .collect(Collectors.toList());
        return emprenimientosOrdenados;
    }
}
