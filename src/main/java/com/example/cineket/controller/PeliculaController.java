package com.example.cineket.controller;

import com.example.cineket.model.Pelicula;
import com.example.cineket.service.PeliculaService;
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
@RequestMapping("/api/peliculas")
@Tag(name = "Películas", description = "Operaciones de gestión de películas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    @Operation(summary = "Obtener todas las películas", description = "Retorna una lista con todas las películas registradas en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de películas obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pelicula.class)))
    public ResponseEntity<List<Pelicula>> getPeliculas(){
        List<Pelicula> listaPeliculas =  peliculaService.getPeliculas();
        return ResponseEntity.ok().body(listaPeliculas);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva película", description = "Crea una nueva película en el sistema con los datos proporcionados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Película creada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pelicula.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    public ResponseEntity<Pelicula> createPelicula(@RequestBody Pelicula pelicula){
        Pelicula peliculaCreada = peliculaService.createPelicula(pelicula);
        return ResponseEntity.created(URI.create("/api/peliculas")).body(peliculaCreada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una película", description = "Actualiza los datos de una película existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Película actualizada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pelicula.class))),
            @ApiResponse(responseCode = "404", description = "Película no encontrada")
    })
    public ResponseEntity<Pelicula> updatePelicula(
            @Parameter(description = "ID de la película a actualizar", example = "1")
            @PathVariable Long idPelicula,
            @RequestBody Pelicula pelicula){
        return ResponseEntity.ok(peliculaService.updatePelicula(idPelicula,pelicula));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una película", description = "Elimina una película del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Película eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Película no encontrada")
    })
    public ResponseEntity<Void> deletePelicula(
            @Parameter(description = "ID de la película a eliminar", example = "1")
            @PathVariable Long id){
        peliculaService.deletePelicula(id);
        return ResponseEntity.noContent().build();
    }

}
