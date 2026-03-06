package com.example.cineket.controller;

import com.example.cineket.model.Asiento;
import com.example.cineket.model.Reserva;
import com.example.cineket.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@Tag(name = "Reservas", description = "Operaciones de gestión de reservas de asientos para funciones")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    @Operation(summary = "Obtener todas las reservas", description = "Retorna una lista con todas las reservas realizadas en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de reservas obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reserva.class)))
    public ResponseEntity<List<Reserva>> getReservas() {
        List<Reserva> listaReserva = reservaService.getReservas();
        return ResponseEntity.ok().body(listaReserva);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva reserva", description = "Crea una nueva reserva de un asiento para una función específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva creada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reserva.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud"),
            @ApiResponse(responseCode = "409", description = "El asiento ya está reservado")
    })
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        Reserva reservaCreada = reservaService.crearReserva(reserva);
        return ResponseEntity.created(URI.create("/api/reservas")).body(reservaCreada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una reserva", description = "Actualiza los datos de una reserva existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva actualizada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reserva.class))),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<Reserva> updateReserva(
            @Parameter(description = "ID de la reserva a actualizar", example = "1")
            @PathVariable Long id,
            @RequestBody Reserva reserva) {
        return ResponseEntity.ok().body(reservaService.updateReserva(id, reserva));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una reserva", description = "Elimina una reserva del sistema (cancela la reserva)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reserva eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<Void> deleteReserva(
            @Parameter(description = "ID de la reserva a eliminar", example = "1")
            @PathVariable Long id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("funcion/{funcionId}")
    @Operation(summary = "Obtener reservas por función", description = "Retorna todas las reservas realizadas para una función específica")
    @ApiResponse(responseCode = "200", description = "Lista de reservas obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reserva.class)))
    public ResponseEntity<List<Reserva>> getReservasByFuncionId(
            @Parameter(description = "ID de la función", example = "1")
            @PathVariable Long funcionId) {
        List<Reserva> reservas = reservaService.getReservasByFuncionId(funcionId);
        return ResponseEntity.ok().body(reservas);
    }

    @GetMapping("asientos/{funcionId}")
    @Operation(summary = "Obtener asientos disponibles", description = "Retorna una lista de todos los asientos disponibles para una función específica")
    @ApiResponse(responseCode = "200", description = "Lista de asientos disponibles obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Asiento.class)))
    public ResponseEntity<List<Asiento>> getAsientosDisponibles(
            @Parameter(description = "ID de la función", example = "1")
            @PathVariable Long funcionId) {
        List<Asiento> asientosList = reservaService.findAsientosDisponibles(funcionId);
        return ResponseEntity.ok().body(asientosList);
    }

    @GetMapping("exists/{funcionId}/{asientoId}")
    @Operation(summary = "Verificar disponibilidad de asiento", description = "Verifica si un asiento específico está disponible para una función")
    @ApiResponse(responseCode = "200", description = "Resultado de disponibilidad obtenido exitosamente")
    public ResponseEntity<Boolean> existsByFuncionIdAndAsientoId(
            @Parameter(description = "ID de la función", example = "1")
            @PathVariable Long funcionId,
            @Parameter(description = "ID del asiento", example = "1")
            @PathVariable Long asientoId) {
        boolean exists = reservaService.existsByFuncionIdAndAsientoId(funcionId, asientoId);
        return ResponseEntity.ok().body(exists);


    }
}