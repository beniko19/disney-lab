package com.alkemy.disney.alkemylab.service;

import com.alkemy.disney.alkemylab.dto.character.CharacterDTO;

import java.util.List;

public interface CharacterService {

    public CharacterDTO save(CharacterDTO dto);

    List<CharacterDTO> getAllCharacters();

    List<CharacterDTO> getByFilters(String name, Integer age, Integer weight, List<Long> movieId, String order);

    void delete(Long id);

    public CharacterDTO getDetailsById(Long id);

    CharacterDTO update(Long id, CharacterDTO character);
}
