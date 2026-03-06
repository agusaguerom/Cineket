package com.example.cineket.controller;

import com.example.cineket.model.Funcion;
import com.example.cineket.service.FuncionService;
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
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/funciones")
@Tag(name = "Funciones", description = "Operaciones de gestión de funciones (horarios de películas)")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;

    @GetMapping
    @Operation(summary = "Obtener todas las funciones", description = "Retorna una lista con todas las funciones programadas en el cine")
    @ApiResponse(responseCode = "200", description = "Lista de funciones obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcion.class)))
    public ResponseEntity<List<Funcion>> getFunciones() {
        List<Funcion> listaFuncion = funcionService.listarFunciones();
        return ResponseEntity.ok().body(listaFuncion);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva función", description = "Crea una nueva función (horario) para una película en una sala específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Función creada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcion.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    public ResponseEntity<Funcion> addFuncion(@RequestBody Funcion funcion) {
        Funcion funcionCreada = funcionService.crearFuncion(funcion);
        return ResponseEntity.created(URI.create("/api/funciones")).body(funcionCreada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una función", description = "Actualiza los datos de una función existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Función actualizada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcion.class))),
            @ApiResponse(responseCode = "404", description = "Función no encontrada")
    })
    public ResponseEntity<Funcion> updateFuncion(@RequestBody Funcion funcion,
            @Parameter(description = "ID de la función a actualizar", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(funcionService.actualizarFuncion(id,  funcion));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una función", description = "Elimina una función del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Función eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Función no encontrada")
    })
    public ResponseEntity<Void> deleteFuncion(
            @Parameter(description = "ID de la función a eliminar", example = "1")
            @PathVariable Long id){
        funcionService.eliminarFuncion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pelicula/{peliculaId}")
    @Operation(summary = "Obtener funciones por película", description = "Retorna todas las funciones de una película específica")
    @ApiResponse(responseCode = "200", description = "Lista de funciones obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcion.class)))
    public ResponseEntity<List<Funcion>> getFuncionesPorPelicula(
            @Parameter(description = "ID de la película", example = "1")
            @PathVariable Long peliculaId){
        List<Funcion> listaFunciones = funcionService.verFuncionPorPelicula(peliculaId);
        return ResponseEntity.ok().body(listaFunciones);
    }

    @GetMapping("/fecha")
    @Operation(summary = "Obtener funciones por fecha y hora", description = "Retorna todas las funciones que se encuentran en una fecha y hora específica")
    @ApiResponse(responseCode = "200", description = "Lista de funciones obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcion.class)))
    public ResponseEntity<List<Funcion>> getFuncionesPorFechaHora(
            @Parameter(description = "Fecha y hora de la función (formato: ISO-8601)", example = "2024-03-15T19:00:00")
            @RequestParam LocalDateTime fechaHora){
        List<Funcion> listaFunciones = funcionService.verFuncionPorFechaHora(fechaHora);
        return ResponseEntity.ok().body(listaFunciones);
    }

    @GetMapping("/sala/{salaId}")
    @Operation(summary = "Obtener funciones por sala", description = "Retorna todas las funciones programadas en una sala específica")
    @ApiResponse(responseCode = "200", description = "Lista de funciones obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcion.class)))
    public ResponseEntity<List<Funcion>> getFuncionesPorSala(
            @Parameter(description = "ID de la sala", example = "1")
            @PathVariable Long salaId){
        List<Funcion> listaFunciones = funcionService.verFuncionPorSala(salaId);
        return ResponseEntity.ok().body(listaFunciones);
    }


}
