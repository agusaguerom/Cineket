package com.example.cineket.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Schema(name = "Asiento", description = "Representa un asiento en una sala de cine, identificado por fila y columna")
public class Asiento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "ID único del asiento", example = "1")
    private Long id;

    @Schema(description = "Número de columna del asiento (posición horizontal)", example = "5", required = true)
    private int columna;

    @Schema(description = "Número de fila del asiento (posición vertical)", example = "3", required = true)
    private int fila;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    @Schema(description = "Sala a la que pertenece el asiento", required = true)
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
