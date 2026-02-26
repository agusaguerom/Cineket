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
        return asientoRepository.findAll();
    }

    @Override
    public Asiento getAsientoById(Long id) {
        return asientoRepository.findById(id).orElse(null);
    }

    @Override
    public Asiento crearAsiento(Asiento asiento) {
        return asientoRepository.save(asiento);
    }

    @Override
    public Asiento updateAsiento(Long id, Asiento asiento) {
        Asiento asientoExistente = asientoRepository.findById(id).orElse(null);

        if(asientoExistente != null){
            asientoExistente.setColumna(asiento.getColumna());
            asientoExistente.setFila(asiento.getFila());
            return asientoRepository.save(asientoExistente);
        }
        return null;
    }

    @Override
    public void deleteAsiento(Long id) {
        asientoRepository.deleteById(id);
    }
}
