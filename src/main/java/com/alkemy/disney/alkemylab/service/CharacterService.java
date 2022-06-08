package com.alkemy.disney.alkemylab.service;

import com.alkemy.disney.alkemylab.dto.CharacterDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CharacterService {

    public CharacterDTO save(CharacterDTO dto);

    List<CharacterDTO> getAllCharacters();
}
