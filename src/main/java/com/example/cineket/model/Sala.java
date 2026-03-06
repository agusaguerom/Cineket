package com.example.cineket.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Schema(name = "Sala", description = "Representa una sala de cine con asientos distribuidos en filas y columnas")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "ID único de la sala", example = "1")
    private Long id;

    @Schema(description = "Nombre de la sala", example = "Sala 1", required = true)
    private String nombre;

    @Schema(description = "Número de filas en la sala", example = "10", required = true)
    private int filas;

    @Schema(description = "Número de columnas (asientos por fila) en la sala", example = "12", required = true)
    private int columnas;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asiento> asientos = new ArrayList<>();

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
    private List<Funcion> funciones = new ArrayList<>();

    public Sala(){}

    public Sala( String nombre, int filas, int columnas) {
        this.nombre = nombre;
        this.filas = filas;
        this.columnas = columnas;
    }

    public Sala(Long id, String nombre, int filas, int columnas) {
        this.id = id;
        this.nombre = nombre;
        this.filas = filas;
        this.columnas = columnas;
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

    public int getFilas() {
        return filas;
    }

    public void setFilas(int capacidad) {
        this.filas = capacidad;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
}
