package com.example.cineket.dto;

import java.time.LocalDateTime;

public class FuncionDto {

    private String nombrePelicula;
    private int duracionPelicula;
    private int numeroSala;
    private LocalDateTime FechaHora;

    public FuncionDto(String nombrePelicula, int duracionPelicula, int numeroSala, LocalDateTime fechaHora) {
        this.nombrePelicula = nombrePelicula;
        this.duracionPelicula = duracionPelicula;
        this.numeroSala = numeroSala;
        FechaHora = fechaHora;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public int getDuracionPelicula() {
        return duracionPelicula;
    }

    public void setDuracionPelicula(int duracionPelicula) {
        this.duracionPelicula = duracionPelicula;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public LocalDateTime getFechaHora() {
        return FechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        FechaHora = fechaHora;
    }
}
