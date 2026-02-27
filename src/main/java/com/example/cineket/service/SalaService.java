package com.example.cineket.service;

import com.example.cineket.model.Asiento;
import com.example.cineket.model.Sala;
import com.example.cineket.repository.AsientoRepository;
import com.example.cineket.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService implements ISalaService{

    @Autowired
    private SalaRepository salaRepository;

    private AsientoRepository asientoRepository;

    @Override
    public List<Sala> getSalas() {
        return salaRepository.findAll();
    }

    @Override
    public Sala crearSala(Sala sala) {
        Sala salacreada = salaRepository.save(sala);

        int filas = sala.getFilas();
        int columnas = sala.getColumnas();

        for (int i = 1; i < filas; i++) {
            for (int j = 1; j < columnas; j++) {
                // Aquí puedes crear una nueva entidad Asiento y asociarla a la sala creada
                // Por ejemplo:
                 Asiento asiento = new Asiento();
                asiento.setFila(i);
                asiento.setColumna(j);
                asiento.setSala(salacreada);
                asientoRepository.save(asiento);
            }
        }


        return salacreada;

    }

    @Override
    public Sala actualizarSala(Long id, Sala sala) {
        Sala salaExistente = salaRepository.findById(id).orElse(null);
       if(salaExistente != null){
           salaExistente.setNombre(sala.getNombre());
           salaExistente.setFilas(sala.getFilas());
           salaExistente.setColumnas(sala.getColumnas());
           return  salaRepository.save(sala);
       }
       return null;
    }

    @Override
    public void eliminarSala(Long id) {
        salaRepository.deleteById(id);
    }
}
