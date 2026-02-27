package com.example.cineket.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcion_id", nullable = false)
    private Funcion funcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asiento_id", nullable = false)
    private Asiento asiento;

    private LocalDateTime fechaReserva;

    public Reserva() {
    }

    public Reserva(Long id, Funcion funcion, Asiento asiento, LocalDateTime fechaReserva) {
        this.id = id;
        this.funcion = funcion;
        this.asiento = asiento;
        this.fechaReserva = fechaReserva;
    }
    public Reserva( Funcion funcion, Asiento asiento, LocalDateTime fechaReserva) {
        this.funcion = funcion;
        this.asiento = asiento;
        this.fechaReserva = fechaReserva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
