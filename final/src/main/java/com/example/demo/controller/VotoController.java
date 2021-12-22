package com.example.demo.controller;

import com.example.demo.entity.Emprendimiento;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Voto;
import com.example.demo.repository.EmprendimientoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.VotoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/votos")
public class VotoController {

    private final VotoRepository votoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmprendimientoRepository emprendimientoRepository;

    public VotoController(VotoRepository votoRepository, UsuarioRepository usuarioRepository, EmprendimientoRepository emprendimientoRepository) {
        this.votoRepository = votoRepository;
        this.usuarioRepository = usuarioRepository;
        this.emprendimientoRepository = emprendimientoRepository;
    }

    @PostMapping
    public ResponseEntity<?> votar(@RequestParam (name = "empId", required = true) Long empId,
                                          @RequestParam (name = "usu", required = true) Long usuId,
                                          @RequestBody @Valid Voto voto){
        Emprendimiento emprendimiento = emprendimientoRepository.findById(empId)
                .orElseThrow(()-> new EntityNotFoundException("Emprendimiento no encontrado"));
        Usuario usuario = usuarioRepository.findById(usuId)
                .orElseThrow(()-> new EntityNotFoundException("Usuario no encontrado"));
        voto.setVotante(usuario);
        voto.setEmprendimiento(emprendimiento);
        return new ResponseEntity<>(votoRepository.save(voto), HttpStatus.CREATED);
    }
}
