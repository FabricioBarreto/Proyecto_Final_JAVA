package com.example.demo.service;

import com.example.demo.entity.Emprendimiento;
import com.example.demo.entity.EventoEnum;
import com.example.demo.entity.Evento;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.EmprendimientoRepository;
import com.example.demo.repository.EventoRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@Service
public class EmprendimientoService {

    @Autowired
    private final EmprendimientoRepository emprendimientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EventoRepository eventoRepository;

    public EmprendimientoService(EmprendimientoRepository emprendimientoRepository, UsuarioRepository usuarioRepository, EventoRepository eventoRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.eventoRepository = eventoRepository;
    }


    public ResponseEntity<?> crearEmprendimiento(Long id){

        Emprendimiento emprendimiento = new Emprendimiento();

        emprendimiento.setNombre(emprendimiento.getNombre());
        emprendimiento.setDescripcion(emprendimiento.getDescripcion());
        emprendimiento.setDescripcion(emprendimiento.getDescripcion());
        emprendimiento.setContenido(emprendimiento.getContenido());
        emprendimiento.setObjetivo(emprendimiento.getObjetivo());
        emprendimiento.setFechaCreacion(LocalDate.now());
        emprendimiento.setTags(emprendimiento.getTags());
        emprendimiento.setOwner(usuarioRepository.getById(id));

        return new ResponseEntity<>(emprendimientoRepository.save(emprendimiento), HttpStatus.OK);
    }

    public Emprendimiento modificarEmprendimiento(Long id) {

        Emprendimiento emprendimientp = emprendimientoRepository.getById(id);

        emprendimientp.setNombre(emprendimientp.getNombre());
        emprendimientp.setContenido(emprendimientp.getContenido());
        emprendimientp.setDescripcion(emprendimientp.getDescripcion());
        emprendimientp.setObjetivo(emprendimientp.getObjetivo());
        emprendimientp.getTags().addAll(emprendimientp.getTags());

        return emprendimientoRepository.save(emprendimientp);
    }

    public void suscribirse(Long eventoId,Long empId) {

        Evento evento = eventoRepository.findById(eventoId).orElseThrow(() -> new EntityNotFoundException("Evento no encontrado"));
        Emprendimiento emprendimiento = emprendimientoRepository.findById(empId).orElseThrow(() -> new EntityNotFoundException("Emprendimiento no encontrado"));

        if(evento.getEstado() == EventoEnum.ABIERTO) {
            evento.agregarSubscriptor(emprendimiento);
            System.out.println("Se ha subscrito a este evento correctamente.");
        } else if (evento.getEstado() == EventoEnum.EN_CURSO){
            System.out.println("El tiempo de subscripcion a este evento a finalizado.");
        } else{
            System.out.println("Este evento a finalizado.");
        }
        emprendimientoRepository.save(emprendimiento);
    }
}
