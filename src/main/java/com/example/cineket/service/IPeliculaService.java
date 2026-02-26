package com.example.cineket.service;

import com.example.cineket.model.Pelicula;

import java.util.List;

public interface IPeliculaService {

    List<Pelicula> getPeliculas();
    Pelicula getPeliculaById(int id);
    Pelicula createPelicula(Pelicula pelicula);
    Pelicula updatePelicula(Long id, Pelicula pelicula);
    void deletePelicula(Long id);
}
