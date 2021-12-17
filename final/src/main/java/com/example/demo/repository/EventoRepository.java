package com.example.demo.repository;


import com.example.demo.entity.Evento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventoRepository extends CrudRepository<Evento, Long> {
        List<Evento> findByUsuario(Long Id);
        List<Evento> findAllById(Long Id);
}

