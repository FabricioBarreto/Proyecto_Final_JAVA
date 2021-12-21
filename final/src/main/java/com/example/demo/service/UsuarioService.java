package com.example.demo.service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(Usuario usuario)  {
        return this.usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id) {
        Usuario usuario = usuarioRepository.getById(id);
        usuario.setNombre(usuario.getNombre());
        usuario.setApellido(usuario.getApellido());
        usuario.setEmail(usuario.getEmail());
        usuario.setCiudad(usuario.getCiudad());
        usuario.setProvincia(usuario.getProvincia());
        usuario.setPais(usuario.getPais());
        usuario.setUsuarioEnum(usuario.getUsuarioEnum());
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("No se encuentro el usuario a eliminar");
        }
    }

    public Map filtrarVotosDeUsuario(Long id){

        usuarioRepository.findVotosByUsuarioId(id);

        return null;
    }

}
