package com.example.cineket.service;

import com.example.cineket.model.Sala;

import java.util.List;

public interface ISalaService {

    List<Sala> getSalas();

    Sala crearSala(Sala sala);

    Sala actualizarSala(Long id, Sala sala);

    void eliminarSala(Long id);
}
