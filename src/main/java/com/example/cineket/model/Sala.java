package com.example.cineket.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private int filas;
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
