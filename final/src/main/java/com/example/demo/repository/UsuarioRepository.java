package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario getById(Long id);
    List<Usuario> getByCiudad(String ciudad);
    List<Usuario> findByfechaCreacionAfter(LocalDateTime fechaDesde);
}

