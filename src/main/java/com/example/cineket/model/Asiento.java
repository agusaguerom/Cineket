package com.example.cineket.model;

import jakarta.persistence.*;

@Entity
public class Asiento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int columna;
    private int fila;

    @ManyToOne
    private Sala sala;
}
