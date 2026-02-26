package com.example.cineket.service;

import com.example.cineket.model.Pelicula;
import com.example.cineket.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaService implements IPeliculaService{

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public List<Pelicula> getPeliculas() {
        return peliculaRepository.findAll();
    }

    @Override
    public Pelicula getPeliculaById(Long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    @Override
    public Pelicula createPelicula(Pelicula pelicula) {
        return null;
    }

    @Override
    public Pelicula updatePelicula(Long id, Pelicula pelicula) {
        return null;
    }

    @Override
    public void deletePelicula(Long id) {

    }
}
