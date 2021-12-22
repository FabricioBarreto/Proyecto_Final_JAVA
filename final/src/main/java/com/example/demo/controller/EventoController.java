package com.example.demo.controller;


import com.example.demo.entity.Emprendimiento;
import com.example.demo.entity.Evento;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Voto;
import com.example.demo.repository.EmprendimientoRepository;
import com.example.demo.repository.EventoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.VotoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/eventos")
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

    @PostMapping (value = "votar/{usuaarioId}/{empId}")
    public ResponseEntity<?> votar(@PathVariable ("empId") Long empId,
                                   @PathVariable ("usuaarioId")Long usuaarioId,
                                   @RequestBody @Valid Voto voto){
        Emprendimiento emprendimiento = emprendimientoRepository.findById(empId)
                .orElseThrow(()-> new EntityNotFoundException("Emprendimiento no encontrado"));
        Usuario usuario = usuarioRepository.findById(usuaarioId)
                .orElseThrow(()-> new EntityNotFoundException("Usuario no encontrado"));
        voto.setVotante(usuario);
        voto.setEmprendimiento(emprendimiento);
        emprendimiento.setCantidadDeVotos(emprendimiento.getCantidadDeVotos()+1);
        return new ResponseEntity<>(votoRepository.save(voto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/ranking")
    public List<Emprendimiento> ranking(@PathVariable("id")Long id){
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

