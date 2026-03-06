package com.example.cineket.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Schema(name = "Pelicula", description = "Representa una película en el cine")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "ID único de la película", example = "1")
    private Long id;

    @Schema(description = "Nombre de la película", example = "Avatar", required = true)
    private String nombre;

    @Schema(description = "Duración de la película en minutos", example = "120", required = true)
    private int duracion;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Funcion> funciones = new ArrayList<>();

    public Pelicula() {
    }

    public Pelicula(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public Pelicula(Long id, String nombre, int duracion) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
