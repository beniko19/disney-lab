package com.alkemy.disney.alkemylab.controller;

import com.alkemy.disney.alkemylab.dto.GenreDTO;
import com.alkemy.disney.alkemylab.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping("getAll")
    public ResponseEntity<List<GenreDTO>> getAll() {
        List<GenreDTO> genres = genreService.getAllGenres();
        return ResponseEntity.ok().body(genres);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO genre) {
        GenreDTO savedGenre = genreService.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

   // @GetMapping(value = "/filter")
    //public ResponseEntity<List<GenreDTO>> getDetailsByFilters(
      //      @RequestParam(required = false) String name,
        //    @RequestParam(required = false) String order
    //) {
        //List<GenreDTO> genres = genreService.ge
    //}
}
