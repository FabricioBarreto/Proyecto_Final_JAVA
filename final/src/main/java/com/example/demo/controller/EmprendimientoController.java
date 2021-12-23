package com.example.demo.controller;

import com.example.demo.entity.Emprendimiento;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.EmprendimientoRepository;
import com.example.demo.repository.EventoRepository;
import com.example.demo.repository.UsuarioRepository;
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
    public final UsuarioRepository usuarioRepository;

    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository, EventoRepository eventoRepository, UsuarioRepository usuarioRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping(value = "/{ownerId}")
    public ResponseEntity<?> crearEmprendimiento(@PathVariable("ownerId") Long ownerId,
            @RequestBody @Valid Emprendimiento emprendimiento){
        Usuario usuario = usuarioRepository
                .findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        usuario.getEmprendimientos().add(emprendimiento);
        emprendimiento.setOwner(usuario);

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
        return new ResponseEntity<>(emprendimientoRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public Emprendimiento modificarEmprendimiento (@PathVariable("id") Long id,
                                         @RequestBody @Valid Emprendimiento emprendimientoRecibido){
        Emprendimiento emprendimiento = emprendimientoRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Emprendimiemto no encontrado"));
        emprendimiento.setNombre(emprendimientoRecibido.getNombre());
        emprendimiento.setDescripcion(emprendimientoRecibido.getDescripcion());
        emprendimiento.setContenido(emprendimientoRecibido.getContenido());
        emprendimiento.setObjetivo(emprendimientoRecibido.getObjetivo());
        emprendimiento.setPublicado(emprendimientoRecibido.isPublicado());
        emprendimiento.setUrl(emprendimientoRecibido.getUrl());
        emprendimiento.setTags(emprendimientoRecibido.getTags());
        return emprendimientoRepository.save(emprendimiento);
    }

    @DeleteMapping(value="/{id}")
    public void eliminarEmprendimiento(@PathVariable("id")Long id){
        Emprendimiento emprendimiento = emprendimientoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Emprendimento no encontrado"));
        emprendimientoRepository.delete(emprendimiento);
    }
}
