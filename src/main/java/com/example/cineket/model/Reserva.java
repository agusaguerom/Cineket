package com.example.cineket.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Schema(name = "Reserva", description = "Representa una reserva de un asiento para una función específica")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "ID único de la reserva", example = "1")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcion_id", nullable = false)
    @Schema(description = "Función para la cual se realiza la reserva", required = true)
    private Funcion funcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asiento_id", nullable = false)
    @Schema(description = "Asiento que se reserva", required = true)
    private Asiento asiento;

    @Schema(description = "Fecha y hora en que se realizó la reserva (formato ISO-8601)", example = "2024-03-10T14:30:00")
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
