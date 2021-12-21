package com.example.demo.repository;

import com.example.demo.entity.Emprendimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprendimientoRepository extends JpaRepository<Emprendimiento, Long> {
    @Query("SELECT e FROM Emprendimiento e join fetch e.tags t WHERE t.tagNmbre LIKE %:tag%")
    List<Emprendimiento> findByTag(@Param("tag") String tag);
    List<Emprendimiento> findByPublicado(boolean publicado );
}
