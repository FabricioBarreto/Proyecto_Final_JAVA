package com.example.demo.controller;

import com.example.demo.entity.Emprendimiento;
import com.example.demo.entity.Evento;
import com.example.demo.repository.EmprendimientoRepository;
import com.example.demo.repository.EventoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/emprendimientos")
public class EmprendimientoController {

    private final EmprendimientoRepository emprendimientoRepository;
    private final EventoRepository eventoRepository;

    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository, EventoRepository eventoRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.eventoRepository = eventoRepository;
    }

    @PostMapping
    public ResponseEntity<?> crearEmprendimiento(@RequestBody @Valid Emprendimiento emprendimiento){
        return new ResponseEntity<>(emprendimientoRepository.save(emprendimiento), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> mostrarEmorendimientos(
            @RequestParam(name = "tag", required = false)String tag,
            @RequestParam(name = "publicado", required = false, defaultValue = "true")boolean publicado){
        if (tag != null){
            return new ResponseEntity<>(emprendimientoRepository.findByTag(tag), HttpStatus.OK);
        }else if (!publicado){
            return new ResponseEntity<>(emprendimientoRepository.findByPublicado(false), HttpStatus.OK);
        }
        return new ResponseEntity<>(emprendimientoRepository.findByActivo(true), HttpStatus.OK);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<?> modificarEmprendimiento (@PathVariable("id") Long emprendimientoId,
                                                      @RequestBody @Valid Emprendimiento emprendimientoRecibido,
                                                      @RequestParam(name = "suscripto", required = false) Long eventoId){
        Emprendimiento emprendimiento = emprendimientoRepository.findById(emprendimientoId)
                .orElseThrow(()->new EntityNotFoundException("Emprendimiemto no encontrado"));
        if(eventoId != null){
            Evento evento = eventoRepository.findById(eventoId)
                    .orElseThrow(()-> new EntityNotFoundException("Evento no encontrado"));
            evento.getEmprendimientosSubscriptos().add(emprendimiento);
            eventoRepository.save(evento);
        }
        emprendimiento.setNombre(emprendimientoRecibido.getNombre());
        emprendimiento.setDescripcion(emprendimientoRecibido.getDescripcion());
        emprendimiento.setContenido(emprendimientoRecibido.getContenido());
        emprendimiento.setObjetivo(emprendimientoRecibido.getObjetivo());
        emprendimiento.setPublicado(emprendimientoRecibido.isPublicado());

        return new ResponseEntity<>(emprendimientoRepository.save(emprendimiento), HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{id}")
    public void eliminarEmprendimiento(@PathVariable("id")Long id){
        Emprendimiento emprendimiento = emprendimientoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Emprendimento no encontrado"));
        emprendimiento.setActivo(false);
        emprendimientoRepository.save(emprendimiento);
    }
}



