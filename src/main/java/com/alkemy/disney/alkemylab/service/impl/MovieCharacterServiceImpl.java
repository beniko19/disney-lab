package com.alkemy.disney.alkemylab.service.impl;

import com.alkemy.disney.alkemylab.repository.MovieCharacterRepository;
import com.alkemy.disney.alkemylab.service.MovieCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieCharacterServiceImpl implements MovieCharacterService {

    @Autowired
    MovieCharacterRepository movieCharacterRepository;



    public void deleteMovieCharacter(Long id) {
        movieCharacterRepository.deleteCharacter(id);

    }


    public void deleteMovieGenre(Long id) {

    }
}
