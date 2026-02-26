package com.example.cineket.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcion_id", nullable = false)
    private Funcion funcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asiento_id", nullable = false)
    private Asiento asiento;

    private LocalDateTime fechaReserva;

}
