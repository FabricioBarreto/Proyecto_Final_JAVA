package com.example.demo.controller;

import com.example.demo.entity.Voto;
import com.example.demo.repository.VotoRepository;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class VotoController {

    @Autowired
    private final UsuarioService usuarioService;
    private final VotoRepository votoRepository;
    private final VotoService votoService;

    public VotoController(UsuarioService usuarioService, VotoRepository votoRepository, VotoService votoService) {
        this.usuarioService = usuarioService;
        this.votoRepository = votoRepository;
        this.votoService = votoService;
    }


    @Transactional
    @PostMapping("/usuarios/{usuarioId}/{emprendimientoId}")
    public ResponseEntity<?> crearVoto(@PathVariable("usuarioId") Long usuarioId,@PathVariable("emrendimientoId")Long emprendimientoId,
                                       @RequestBody @Valid Voto voto) {
        return new ResponseEntity<>(votoService.crearVoto(usuarioId,emprendimientoId), HttpStatus.OK);
    }

}
