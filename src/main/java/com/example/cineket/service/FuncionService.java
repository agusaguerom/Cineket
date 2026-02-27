package com.example.cineket.service;

import com.example.cineket.model.Funcion;
import com.example.cineket.repository.FuncionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionService implements IFuncionService {


    private FuncionRepository funcionRepository;

    @Override
    public List<Funcion> listarFunciones() {
        return funcionRepository.findAll();
    }

    @Override
    public Funcion obtenerFuncionPorId(Long id) {
        return funcionRepository.findById(id).orElse(null);
    }

    @Override
    public Funcion crearFuncion(Funcion funcion) {
        return funcionRepository.save(funcion);
    }

    @Override
    public Funcion actualizarFuncion(Long id, Funcion funcion) {
        Funcion funcionExistente = funcionRepository.findById(id).orElse(null);
        if(funcionExistente != null){
            funcionExistente.setPelicula(funcion.getPelicula());
            funcionExistente.setSala(funcion.getSala());
            funcionExistente.setFechaHora(funcion.getFechaHora());
            return funcionRepository.save(funcionExistente);
        }
        return null;
    }

    @Override
    public void eliminarFuncion(Long id) {
        funcionRepository.deleteById(id);
    }
}
