package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Voto> findVotosByUsuarioId(Long usuarioId);
    List<Usuario> findByCiudad(String ciudad);
    List<Usuario> findByfechaCreacionAfter(LocalDateTime fechaCreacion);
}

