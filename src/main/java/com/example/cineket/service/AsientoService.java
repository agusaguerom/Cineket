package com.example.cineket.service;

import com.example.cineket.model.Asiento;
import com.example.cineket.repository.AsientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsientoService implements IAsientoService{

    @Autowired
    private AsientoRepository asientoRepository;

    @Override
    public List<Asiento> getAsientos() {
        return List.of();
    }

    @Override
    public Asiento getAsientoById(Long id) {
        return null;
    }

    @Override
    public Asiento crearAsiento(Asiento asiento) {
        return null;
    }

    @Override
    public Asiento updateAsiento(Long id, Asiento asiento) {
        return null;
    }

    @Override
    public void deleteAsiento(Long id) {

    }
}
