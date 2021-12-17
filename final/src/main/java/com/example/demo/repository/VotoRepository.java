package com.example.demo.repository;

import com.example.demo.entity.Usuario;
import com.example.demo.entity.Voto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends CrudRepository<Voto, Long> {
    List<Voto> findByUsuario(Usuario usuario);
    List<Voto> findVotoById(Long id);
    List<Voto> findAllById(Long id);
}
