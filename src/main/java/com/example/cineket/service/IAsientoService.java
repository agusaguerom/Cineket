package com.example.cineket.service;

import com.example.cineket.model.Asiento;

import java.util.List;

public interface IAsientoService {

    List<Asiento> getAsientos();
    Asiento getAsientoById(Long id);
    Asiento crearAsiento(Asiento asiento);
    Asiento updateAsiento(Long id, Asiento asiento);
    void deleteAsiento(Long id);
}
