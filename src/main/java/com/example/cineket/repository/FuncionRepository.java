package com.example.cineket.repository;

import com.example.cineket.model.Funcion;
import com.example.cineket.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FuncionRepository extends JpaRepository<Funcion, Long> {

    List<Funcion> findByPeliculaId(Long peliculaId);
    List<Funcion> findByFechaHora(LocalDateTime fechaHora);
    List<Funcion> findFuncionBySalaId(Long salaId);
}
