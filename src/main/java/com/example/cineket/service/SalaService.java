package com.example.cineket.service;

import com.example.cineket.model.Sala;
import com.example.cineket.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService implements ISalaService{

    @Autowired
    private SalaRepository salaRepository;


    @Override
    public List<Sala> getSalas() {
        return salaRepository.findAll();
    }

    @Override
    public Sala crearSala(Sala sala) {
        return salaRepository.save(sala);
    }

    @Override
    public Sala actualizarSala(Long id, Sala sala) {
        Sala salaExistente = salaRepository.findById(id).orElse(null);
       if(salaExistente != null){
           salaExistente.setNombre(sala.getNombre());
           salaExistente.setCapacidad(sala.getCapacidad());
           return  salaRepository.save(sala);
       }
       return null;
    }

    @Override
    public void eliminarSala(Long id) {
        salaRepository.deleteById(id);
    }
}
