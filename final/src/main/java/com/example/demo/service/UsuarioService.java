package com.example.demo.service;

import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final VotoRepository votoRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, VotoRepository votoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.votoRepository = votoRepository;
    }



}
