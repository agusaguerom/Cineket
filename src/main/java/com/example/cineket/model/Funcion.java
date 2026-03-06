package com.example.cineket.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Schema(name = "Funcion", description = "Representa una función o horario de proyección de una película en una sala específica")
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "ID único de la función", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pelicula_id", nullable = false)
    @Schema(description = "Película a proyectar", required = true)
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    @Schema(description = "Sala donde se proyectará la película", required = true)
    private Sala sala;

    @Schema(description = "Fecha y hora de la función (formato ISO-8601)", example = "2024-03-15T19:00:00", required = true)
    private LocalDateTime fechaHora;



    public Funcion(){}

    public Funcion(Long id, Pelicula pelicula, Sala sala, LocalDateTime fechaHora) {
        this.id = id;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fechaHora = fechaHora;
    }

    public Funcion(Pelicula pelicula, Sala sala, LocalDateTime fechaHora) {
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
