package com.example.cineket.controller;

import com.example.cineket.model.Funcion;
import com.example.cineket.service.FuncionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/funciones")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;

    @GetMapping
    public ResponseEntity<List<Funcion>> getFunciones() {
        List<Funcion> listaFuncion = funcionService.listarFunciones();
        return ResponseEntity.ok().body(listaFuncion);
    }

    @PostMapping
    public ResponseEntity<Funcion> addFuncion(@RequestBody Funcion funcion) {
        Funcion funcionCreada = funcionService.crearFuncion(funcion);
        return ResponseEntity.created(URI.create("/api/funciones")).body(funcionCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcion> updateFuncion(@RequestBody Funcion funcion, @PathVariable Long id) {
        return ResponseEntity.ok(funcionService.actualizarFuncion(id,  funcion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncion(@PathVariable Long id){
        funcionService.eliminarFuncion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pelicula/{peliculaId}")
    public ResponseEntity<List<Funcion>> getFuncionesPorPelicula(@PathVariable Long peliculaId){
        List<Funcion> listaFunciones = funcionService.verFuncionPorPelicula(peliculaId);
        return ResponseEntity.ok().body(listaFunciones);
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<Funcion>> getFuncionesPorFechaHora(@RequestParam LocalDateTime fechaHora){
        List<Funcion> listaFunciones = funcionService.verFuncionPorFechaHora(fechaHora);
        return ResponseEntity.ok().body(listaFunciones);
    }

    @GetMapping("/sala/{salaId}")
    public ResponseEntity<List<Funcion>> getFuncionesPorSala(@PathVariable Long salaId){
        List<Funcion> listaFunciones = funcionService.verFuncionPorSala(salaId);
        return ResponseEntity.ok().body(listaFunciones);
    }


}
