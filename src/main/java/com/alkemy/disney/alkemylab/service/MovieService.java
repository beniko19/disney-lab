package com.alkemy.disney.alkemylab.service;

import com.alkemy.disney.alkemylab.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    public MovieDTO save(MovieDTO dto);
    List<MovieDTO> getAllMovies();
}
