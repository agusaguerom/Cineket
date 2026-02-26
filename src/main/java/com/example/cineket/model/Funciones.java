package com.example.cineket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Funciones {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Pelicula pelicula;

    private Sala sala;

    private LocalDateTime fechaHora;

    public Funciones(){}

    public Funciones(Long id, Pelicula pelicula, Sala sala, LocalDateTime fechaHora) {
        this.id = id;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fechaHora = fechaHora;
    }

    public Funciones( Pelicula pelicula, Sala sala, LocalDateTime fechaHora) {
        this.pelicula = pelicula;
        this.sala = sala;
        this.fechaHora = fechaHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
