package com.example.demo.repository;

import java.util.List;
import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByCiudad(String ciudad);
    List<Usuario> findByActivo(boolean activo);
}

