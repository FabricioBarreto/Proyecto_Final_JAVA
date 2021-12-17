package com.example.demo.controller;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.example.demo.entity.Usuario;
import com.example.demo.entity.Voto;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VotoController {

    /*

    @Autowired
    private UsuarioRepository usuarioRepository;
    private VotoRepository votoRepository;


    public VotoController(UsuarioRepository usuarioRepository, VotoRepository votoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.votoRepository = votoRepository;
    }

    @GetMapping("/votos")
    public ResponseEntity<?> buscar() {
        return new ResponseEntity<>(votoRepository.findAll(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/usuarios/{id}/votos")
    public ResponseEntity<?> crear(@PathVariable("id") Long id,
            @RequestBody @Valid Voto voto) {
        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        usuario.getVotos().add(voto);
        voto.setUsuario(usuario);

        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @GetMapping(value = "/votos", params = "usuario")
    public ResponseEntity<?> votosAFiltrar(@RequestParam Usuario usuario) {

        return new ResponseEntity<>(votoRepository.findByUsuario(usuario), HttpStatus.OK);
    }

     */
}
