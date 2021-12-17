package com.example.demo.repository;

import com.example.demo.entity.Emprendimiento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmprendimientoRepository extends CrudRepository<Emprendimiento, Long> {
    List<Emprendimiento> findByUsuario(Long Id);
    List<Emprendimiento> getByTags(String emprendimientosAFiltrar);
    List<Emprendimiento> getByPublicado(Boolean emprendimientosAFiltrar );
    
}
