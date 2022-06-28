package com.alkemy.disney.alkemylab.controller;

import com.alkemy.disney.alkemylab.dto.movie.MovieDTO;
import com.alkemy.disney.alkemylab.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<MovieDTO>> getAll() {
        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity.ok().body(movies);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie){
        MovieDTO savedMovie = movieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String tittle,
            @RequestParam(required = false, defaultValue = "ASC") String order,
            @RequestParam(required = false) List<Long> genreId
    ) {
        List<MovieDTO> movies = movieService.getByFilters(tittle, order, genreId);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/getMovie/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id) {
        MovieDTO result = movieService.getMovieById(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/addCharacters/{charactersId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<MovieDTO> addCharactersToMovie(@PathVariable Long id, @PathVariable List<Long> charactersId) {
        MovieDTO result = movieService.addCharactersToMovie(id, charactersId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("{id}/removeCharacters/{charactersId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieDTO> removeCharactersFromMovie(@PathVariable Long id, @PathVariable List<Long> charactersId) {
        MovieDTO result = movieService.removeCharactersFromMovie(id, charactersId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO movie) {
        MovieDTO result = movieService.update(id, movie);
        return ResponseEntity.ok().body(result);
    }

}
