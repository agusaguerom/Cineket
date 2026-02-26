package com.example.cineket.model;

import jakarta.persistence.*;

@Entity
public class Asiento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int columna;
    private int fila;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;
}
