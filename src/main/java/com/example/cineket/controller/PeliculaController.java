package com.example.cineket.controller;

import com.example.cineket.model.Pelicula;
import com.example.cineket.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public ResponseEntity<List<Pelicula>> getPeliculas(){
        List<Pelicula> listaPeliculas =  peliculaService.getPeliculas();
        return ResponseEntity.ok().body(listaPeliculas);
    }

    @PostMapping
    public ResponseEntity<Pelicula> createPelicula(@RequestBody Pelicula pelicula){
        Pelicula peliculaCreada = peliculaService.createPelicula(pelicula);
        return ResponseEntity.created(URI.create("/api/peliculas")).body(peliculaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> updatePelicula(@PathVariable Long idPelicula,  @RequestBody Pelicula pelicula){
        return ResponseEntity.ok(peliculaService.updatePelicula(idPelicula,pelicula));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelicula(@PathVariable Long id){
        peliculaService.deletePelicula(id);
        return ResponseEntity.noContent().build();
    }

}
