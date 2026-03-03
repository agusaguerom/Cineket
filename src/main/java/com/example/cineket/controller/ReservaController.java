package com.example.cineket.controller;

import com.example.cineket.model.Asiento;
import com.example.cineket.model.Reserva;
import com.example.cineket.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<Reserva>> getReservas() {
        List<Reserva> listaReserva = reservaService.getReservas();
        return ResponseEntity.ok().body(listaReserva);
    }

    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        Reserva reservaCreada = reservaService.crearReserva(reserva);
        return ResponseEntity.created(URI.create("/api/reservas")).body(reservaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        return ResponseEntity.ok().body(reservaService.updateReserva(id, reserva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("funcion/{funcionId}")
    public ResponseEntity<List<Reserva>> getReservasByFuncionId(@PathVariable Long funcionId) {
        List<Reserva> reservas = reservaService.getReservasByFuncionId(funcionId);
        return ResponseEntity.ok().body(reservas);
    }

    @GetMapping("asientos/{funcionId}")
    public ResponseEntity<List<Asiento>> getAsientosDisponibles(@PathVariable Long funcionId) {
        List<Asiento> asientosList = reservaService.findAsientosDisponibles(funcionId);
        return ResponseEntity.ok().body(asientosList);
    }

    @GetMapping("exists/{funcionId}/{asientoId}")
    public ResponseEntity<Boolean> existsByFuncionIdAndAsientoId(@PathVariable Long funcionId, @PathVariable Long asientoId) {
        boolean exists = reservaService.existsByFuncionIdAndAsientoId(funcionId, asientoId);
        return ResponseEntity.ok().body(exists);


    }
}