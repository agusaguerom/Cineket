package com.example.cineket.service;

import com.example.cineket.model.Reserva;

import java.util.List;

public interface IReservaService {
    List<Reserva> getReservas();
    Reserva getReservaById(Long id);
    Reserva crearReserva(Reserva reserva);
    Reserva updateReserva(Long id, Reserva reserva);
    void deleteReserva(Long id);
}
