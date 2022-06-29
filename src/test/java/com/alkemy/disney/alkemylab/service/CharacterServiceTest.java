package com.alkemy.disney.alkemylab.service;

import com.alkemy.disney.alkemylab.dto.character.CharacterDTO;
import com.alkemy.disney.alkemylab.entity.CharacterEntity;
import com.alkemy.disney.alkemylab.mapper.CharacterMapper;
import com.alkemy.disney.alkemylab.repository.CharacterRepository;
import com.alkemy.disney.alkemylab.service.impl.CharacterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CharacterServiceTest {
    @Mock
    private CharacterRepository characterRepository;
    @Mock
    private CharacterMapper characterMapper;
    @Mock
    private CharacterDTO characterDTO;
    @InjectMocks
    private CharacterServiceImpl characterService;
    private CharacterEntity characterEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        setUpCharacterEntity();
        setUpCharacterDTO();
    }

    @Test
    void getAllCharacters() {
        when(characterRepository.findAll()).thenReturn(Arrays.asList(characterEntity));
        assertNotNull(characterService.getAllCharacters());
    }

    @Test
    void save() {
        setUpWhenMethod2Save();
        CharacterDTO result = characterService.save(characterDTO);
        assertEquals(characterDTO, result);
    }

    @Test
    void getByFilters() {
    }

    @Test
    void delete() {
    }

    @Test
    void getDetailsById() {
    }

    @Test
    void update() {
    }

    private void setUpCharacterDTO() {
        characterDTO = new CharacterDTO();
        characterDTO.setId(new Long(1));
        characterDTO.setWeight(50);
        characterDTO.setName("Beniko");
        characterDTO.setImage("beniko.png");
        characterDTO.setBackground("testing mockito");
        characterDTO.setAge(23);
        characterDTO.setMovies(new ArrayList<>());
    }

    private void setUpCharacterEntity() {
        characterEntity = new CharacterEntity();
        characterEntity.setId(new Long(1));
        characterEntity.setWeight(50);
        characterEntity.setName("Beniko");
        characterEntity.setImage("beniko.png");
        characterEntity.setAge(23);
        characterEntity.setBackground("testing mockito");
        characterEntity.setDeleted(false);
        characterEntity.setMovies(new ArrayList<>());
    }

    private void setUpWhenMethod2Save() {
        when(characterRepository.save(any())).thenReturn(characterEntity);
        when(characterMapper.characterEntity2DTO(characterEntity)).thenReturn(characterDTO);
        when(characterMapper.characterDTO2Entity(characterDTO)).thenReturn(characterEntity);
    }
}