package com.alkemy.disney.alkemylab.service;

import com.alkemy.disney.alkemylab.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    public GenreDTO save(GenreDTO dto);

    List<GenreDTO> getAllGenres();
}
