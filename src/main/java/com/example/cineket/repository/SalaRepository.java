package com.example.cineket.repository;

import com.example.cineket.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Reserva, Long> {
}
