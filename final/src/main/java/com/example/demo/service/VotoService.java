package com.example.demo.service;

import com.example.demo.entity.Emprendimiento;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Voto;
import com.example.demo.repository.EmprendimientoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;


@Service
public class VotoService {

    @Autowired
    private final VotoRepository votoRepository;
    private final EmprendimientoRepository emprendimientoRepository;
    private final UsuarioRepository usuarioRepository;

    public VotoService(VotoRepository votoRepository, EmprendimientoRepository emprendimientoRepository, UsuarioRepository usuarioRepository) {
        this.votoRepository = votoRepository;
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ResponseEntity<?> crearVoto(Long usuarioId, Long emprendimientoId) {

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        Emprendimiento emprendimiento = emprendimientoRepository.findById(emprendimientoId).orElseThrow(() -> new EntityNotFoundException("Emprendimiento no encontrado"));

        Voto voto = new Voto();
        voto.setVotoEnum(voto.getVotoEnum());
        voto.setVotante(usuario);
        voto.setFechaCreacion(LocalDate.now());

        usuario.getVotos().add(voto);
        emprendimiento.setCantidadDeVotos(emprendimiento.getCantidadDeVotos()+1);


        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }
}
