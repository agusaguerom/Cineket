package com.example.cineket.controller;


import com.example.cineket.model.Asiento;
import com.example.cineket.service.AsientoService;
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
@RequestMapping("/api/asientos")
@Tag(name = "Asientos", description = "Operaciones de gestión de asientos en las salas")
public class AsientoController {

    @Autowired
    private AsientoService asientoService;

    @GetMapping
    @Operation(summary = "Obtener todos los asientos", description = "Retorna una lista con todos los asientos disponibles en todas las salas")
    @ApiResponse(responseCode = "200", description = "Lista de asientos obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Asiento.class)))
    public ResponseEntity<List<Asiento>> getAsientos(){
        List<Asiento> asientos = asientoService.getAsientos();
        return ResponseEntity.ok().body(asientos);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo asiento", description = "Crea un nuevo asiento en una sala específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Asiento creado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Asiento.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    public ResponseEntity<Asiento> crearAsiento(@RequestBody Asiento asiento){
        Asiento asientocreado = asientoService.crearAsiento(asiento);
        return ResponseEntity.created(URI.create("/api/asientos")).body(asientocreado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un asiento", description = "Actualiza los datos de un asiento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asiento actualizado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Asiento.class))),
            @ApiResponse(responseCode = "404", description = "Asiento no encontrado")
    })
    public ResponseEntity<Asiento> actualizarAsiento(
            @Parameter(description = "ID del asiento a actualizar", example = "1")
            @PathVariable Long id,
            @RequestBody Asiento asiento){
        return ResponseEntity.ok(asientoService.updateAsiento(id, asiento));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un asiento", description = "Elimina un asiento de la sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Asiento eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Asiento no encontrado")
    })
    public ResponseEntity<Void> eliminarAsiento(
            @Parameter(description = "ID del asiento a eliminar", example = "1")
            @PathVariable Long id){
        asientoService.deleteAsiento(id);
        return ResponseEntity.noContent().build();
    }


}
