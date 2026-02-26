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

    public Asiento(){}
    public Asiento( int columna, int fila, Sala sala) {
        this.columna = columna;
        this.fila = fila;
        this.sala = sala;
    }

    public Asiento(Long id, int columna, int fila, Sala sala) {
        this.id = id;
        this.columna = columna;
        this.fila = fila;
        this.sala = sala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
