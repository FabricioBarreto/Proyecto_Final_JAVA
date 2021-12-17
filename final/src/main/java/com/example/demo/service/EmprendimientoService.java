package com.example.demo.service;

import com.example.demo.dto.RegistrarEventoDto;
import com.example.demo.entity.Emprendimiento;
import com.example.demo.entity.Tag;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.EmprendimientoRepository;
import com.example.demo.repository.TagsRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EmprendimientoService {

    private final EmprendimientoRepository emprendimientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TagsRepository tagsRepository;

    @Autowired
    public EmprendimientoService(EmprendimientoRepository emprendimientoRepository, UsuarioRepository usuarioRepository, TagsRepository tagsRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.tagsRepository = tagsRepository;
    }

    public crearEmprendimiento(RegistrarEventoDto registrarEventoDto){
        Usuario usuario = usuarioRepository.findById(registrarEventoDto.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        List<Tag> tag = tagsRepository.findAllById(registrarEventoDto.getTags());
        Emprendimiento emprendimiento = new Emprendimiento();
        emprendimiento.setNombre(registrarEventoDto.getNombre());
        emprendimiento.setDescripcion(registrarEventoDto.getDescripcion());
        emprendimiento.setOwner(usuario);
        emprendimiento.getTags().addAll(tag);
    }
}
