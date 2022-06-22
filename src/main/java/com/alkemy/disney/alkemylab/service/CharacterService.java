package com.alkemy.disney.alkemylab.service;

import com.alkemy.disney.alkemylab.dto.CharacterDTO;
import com.alkemy.disney.alkemylab.entity.MovieEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    public CharacterDTO save(CharacterDTO dto);

    List<CharacterDTO> getAllCharacters();

    List<CharacterDTO> getByFilters(String name, Integer age, Integer weight, String movieName, String order);

    void delete(Long id);

    CharacterDTO update(Long id, CharacterDTO character);
}
