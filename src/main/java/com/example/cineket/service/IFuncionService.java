package com.example.cineket.service;

import com.example.cineket.model.Funcion;

import java.util.List;

public interface IFuncionService {

    List<Funcion> listarFunciones();
    Funcion obtenerFuncionPorId(Long id);
    Funcion crearFuncion(Funcion funcion);
    Funcion actualizarFuncion(Long id, Funcion funcion);
    void eliminarFuncion(Long id);
}
