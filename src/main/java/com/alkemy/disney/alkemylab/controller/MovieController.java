package com.alkemy.disney.alkemylab.controller;

import com.alkemy.disney.alkemylab.dto.CharacterDTO;
import com.alkemy.disney.alkemylab.dto.GenreDTO;
import com.alkemy.disney.alkemylab.dto.MovieDTO;
import com.alkemy.disney.alkemylab.entity.CharacterEntity;
import com.alkemy.disney.alkemylab.entity.GenreEntity;
import com.alkemy.disney.alkemylab.entity.MovieEntity;
import com.alkemy.disney.alkemylab.service.MovieService;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("getAll")
    public ResponseEntity<List<MovieDTO>> getAll() {
        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity.ok().body(movies);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie){
        MovieDTO savedMovie = movieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String tittle,
            @RequestParam(required = false) String order,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) Long characterId,
            @RequestParam(required = false) Long genreId
    ) {
        List<MovieDTO> movies = movieService.getByFilters(tittle, order, rating, characterId, genreId);
        return ResponseEntity.ok(movies);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id) {
        MovieDTO result = movieService.getMovieById(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(value = "/{id}/characters/{charactersId}")
    public ResponseEntity<MovieDTO> addCharactersToMovie(@PathVariable Long id, @PathVariable List<Long> charactersId) {
        MovieDTO result = movieService.addCharactersToMovie(id, charactersId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping(value = "{id}/characters/{charactersId}")
    public ResponseEntity<MovieDTO> removeCharactersFromMovie(@PathVariable Long id, @PathVariable List<Long> charactersId) {
        MovieDTO result = movieService.removeCharactersFromMovie(id, charactersId);
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO movie) {
        MovieDTO result = movieService.update(id, movie);
        return ResponseEntity.ok().body(result);
    }

}
