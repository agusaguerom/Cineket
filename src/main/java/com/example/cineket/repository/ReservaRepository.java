package com.example.cineket.repository;

import com.example.cineket.model.Asiento;
import com.example.cineket.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long>
{

    boolean existsByFuncionIdAndAsientoId(Long funcionId, Long asientoId);
    List<Reserva> findByFuncionId(Long funcionId);

    @Query("SELECT a FROM Asiento a WHERE a.sala.id = " +
            "(SELECT f.sala.id FROM Funcion f WHERE f.id = :funcionId) " +
            "AND a.id NOT IN " +
            "(SELECT r.asiento.id FROM Reserva r WHERE r.funcion.id = :funcionId)")
    List<Asiento> findAsientosDisponibles(@Param("funcionId") Long funcionId);
}

