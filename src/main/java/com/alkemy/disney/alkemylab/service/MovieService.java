package com.alkemy.disney.alkemylab.service;

import com.alkemy.disney.alkemylab.dto.CharacterDTO;
import com.alkemy.disney.alkemylab.dto.GenreDTO;
import com.alkemy.disney.alkemylab.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    public MovieDTO save(MovieDTO dto);
    List<MovieDTO> getAllMovies();

    List<MovieDTO> getByFilters(String tittle, String order, Integer rating/*, List<CharacterDTO> characters, List<GenreDTO> genres*/);
}
