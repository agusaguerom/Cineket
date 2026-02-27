package com.example.cineket.controller;


import com.example.cineket.model.Asiento;
import com.example.cineket.service.AsientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/asientos")
public class AsientoController {

    @Autowired
    private AsientoService asientoService;

    @GetMapping
    public ResponseEntity<List<Asiento>> getAsientos(){
        List<Asiento> asientos = asientoService.getAsientos();
        return ResponseEntity.ok().body(asientos);
    }

    @PostMapping
    public ResponseEntity<Asiento> crearAsiento(@RequestBody Asiento asiento){
        Asiento asientocreado = asientoService.crearAsiento(asiento);
        return ResponseEntity.created(URI.create("/api/asientos")).body(asientocreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asiento> actualizarAsiento(@PathVariable Long id, @RequestBody Asiento asiento){
        return ResponseEntity.ok(asientoService.updateAsiento(id, asiento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsiento(@PathVariable Long id){
        asientoService.deleteAsiento(id);
        return ResponseEntity.noContent().build();
    }


}
