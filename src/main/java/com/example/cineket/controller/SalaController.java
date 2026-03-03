package com.example.cineket.controller;

import com.example.cineket.model.Sala;
import com.example.cineket.service.ISalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    @Autowired
    private ISalaService salaService;

    @GetMapping
    public ResponseEntity<List<Sala>> getSalas(){
        List<Sala> salas = salaService.getSalas();
        return ResponseEntity.ok().body(salas);
    }

    @PostMapping
    public ResponseEntity<Sala> crearSala(@RequestBody  Sala sala){
        Sala salacreada = salaService.crearSala(sala);
        return ResponseEntity.created(URI.create("/api/salas" + salacreada.getId())).body(salacreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> actualizarSala(@PathVariable Long id,@RequestBody Sala sala){
        return ResponseEntity.ok(salaService.actualizarSala(id, sala));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSala(@PathVariable Long id){
        salaService.eliminarSala(id);
        return ResponseEntity.noContent().build();
    }

}
