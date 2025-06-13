package com.ejemplo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaludoRepository extends JpaRepository<Saludo, Long> {
    
    // Encontrar saludos por nombre
    List<Saludo> findByNombre(String nombre);
    
    // Encontrar los últimos N saludos
    @Query("SELECT s FROM Saludo s ORDER BY s.fechaCreacion DESC")
    List<Saludo> findLatestSaludos();
    
    // Contar total de saludos
    long countBy();
}