package com.example.cineket.controller;

import com.example.cineket.model.Sala;
import com.example.cineket.service.ISalaService;
import com.example.cineket.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/salas")
@Tag(name = "Salas", description = "Operaciones de gestión de salas de cine")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping
    @Operation(summary = "Obtener todas las salas", description = "Retorna una lista con todas las salas disponibles en el cine")
    @ApiResponse(responseCode = "200", description = "Lista de salas obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sala.class)))
    public ResponseEntity<List<Sala>> getSalas(){
        List<Sala> salas = salaService.getSalas();
        return ResponseEntity.ok().body(salas);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva sala", description = "Crea una nueva sala en el cine con los datos proporcionados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sala creada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sala.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    public ResponseEntity<Sala> crearSala(@RequestBody  Sala sala){
        Sala salacreada = salaService.crearSala(sala);
        return ResponseEntity.created(URI.create("/api/salas" + salacreada.getId())).body(salacreada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una sala", description = "Actualiza los datos de una sala existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sala actualizada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sala.class))),
            @ApiResponse(responseCode = "404", description = "Sala no encontrada")
    })
    public ResponseEntity<Sala> actualizarSala(
            @Parameter(description = "ID de la sala a actualizar", example = "1")
            @PathVariable Long id,
            @RequestBody Sala sala){
        return ResponseEntity.ok(salaService.actualizarSala(id, sala));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una sala", description = "Elimina una sala del cine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sala eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Sala no encontrada")
    })
    public ResponseEntity<Void> eliminarSala(
            @Parameter(description = "ID de la sala a eliminar", example = "1")
            @PathVariable Long id){
        salaService.eliminarSala(id);
        return ResponseEntity.noContent().build();
    }

}
