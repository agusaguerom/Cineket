package com.example.cineket.repository;

import com.example.cineket.model.Reserva;
import com.example.cineket.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SalaRepository extends JpaRepository<Sala, Long> {
}
