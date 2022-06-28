package com.alkemy.disney.alkemylab.service;

import com.alkemy.disney.alkemylab.dto.movie.MovieDTO;

import java.util.List;

public interface MovieService {

    public MovieDTO save(MovieDTO dto);
    List<MovieDTO> getAllMovies();

    List<MovieDTO> getByFilters(String tittle, String order, List<Long> genreId);

    void delete(Long id);

    MovieDTO update(Long id, MovieDTO movie);

    MovieDTO getMovieById(Long id);

    MovieDTO addCharactersToMovie(Long id, List<Long> charactersId);

    MovieDTO removeCharactersFromMovie(Long id, List<Long> charactersId);
}
