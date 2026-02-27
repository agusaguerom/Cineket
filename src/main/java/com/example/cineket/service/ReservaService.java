package com.example.cineket.service;

import com.example.cineket.model.Reserva;
import com.example.cineket.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService implements IReservaService{

    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> getReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva getReservaById(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    @Override
    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva updateReserva(Long id, Reserva reserva) {
        Reserva reservaExistente = reservaRepository.findById(id).orElse(null);

        if(reservaExistente != null){
            reservaExistente.setAsiento(reserva.getAsiento());
            reservaExistente.setFuncion(reserva.getFuncion());
            reservaExistente.setFechaReserva(reserva.getFechaReserva());
            return reservaRepository.save(reservaExistente);
        }
        return null;
    }

    @Override
    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}
