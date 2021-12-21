package com.example.demo.controller;

import com.example.demo.repository.EmprendimientoRepository;
import com.example.demo.service.EmprendimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprendimiento")
public class EmprendimientoController {

    @Autowired
    private final EmprendimientoRepository emprendimientoRepository;
    private final EmprendimientoService emprendimientoService;

    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository, EmprendimientoService emprendimientoService) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.emprendimientoService = emprendimientoService;
    }


    @PostMapping
    public ResponseEntity<?> crearEmprendimiento(@PathVariable("id") Long id){
        return new ResponseEntity<>(emprendimientoService.crearEmprendimiento(id), HttpStatus.OK);
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

    @PutMapping(value = "{id}")
    public ResponseEntity<?> modiicarEmporendimiento(@PathVariable("id") Long id){
        return new ResponseEntity<>(emprendimientoService.modificarEmprendimiento(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public void eliminarEmprendimiento(@PathVariable("id") Long id){
        try {
            emprendimientoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("No se encuentra el emprendimiento a eliminar");
        }
    }
}



