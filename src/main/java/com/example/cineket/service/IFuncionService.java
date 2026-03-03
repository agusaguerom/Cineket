package com.example.cineket.service;

import com.example.cineket.model.Funcion;

import java.time.LocalDateTime;
import java.util.List;

public interface IFuncionService {

    List<Funcion> listarFunciones();
    Funcion obtenerFuncionPorId(Long id);
    Funcion crearFuncion(Funcion funcion);
    Funcion actualizarFuncion(Long id, Funcion funcion);
    void eliminarFuncion(Long id);

    List<Funcion> verFuncionPorPelicula(Long peliculaId);
    List<Funcion> verFuncionPorFechaHora(LocalDateTime fechaHora);
    List<Funcion> verFuncionPorSala(Long salaId);
}
