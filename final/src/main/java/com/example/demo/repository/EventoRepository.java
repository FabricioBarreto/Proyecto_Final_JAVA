package com.example.demo.repository;


import com.example.demo.entity.Evento;
import com.example.demo.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventoRepository extends CrudRepository<Evento, Long> {
        List<Evento> findAllById(Long Id);
}

