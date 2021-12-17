package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    List<Usuario> getById(Long id);
    List<Usuario> getByCiudad(String ciudad);
    List<Usuario> findByfechaCreacionAfter(LocalDate fechaCreacion);
}

