package com.alkemy.disney.alkemylab.service;

import com.alkemy.disney.alkemylab.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    public MovieDTO save(MovieDTO dto);
    List<MovieDTO> getAllMovies();

    List<MovieDTO> getByFilters(String tittle, String order, Integer rating, Long characterId, Long genreId);

    void delete(Long id);

    MovieDTO addCharactersToMovie(Long movieId, List<Long> charactersId);

    MovieDTO removeCharactersFromMovie(Long movieId, List<Long> charactersId);

    MovieDTO update(Long id, MovieDTO movie);

    MovieDTO getMovieById(Long id);
}
