package com.alkemy.disney.alkemylab.service.impl;

import com.alkemy.disney.alkemylab.dto.CharacterDTO;
import com.alkemy.disney.alkemylab.dto.CharacterFiltersDTO;
import com.alkemy.disney.alkemylab.entity.CharacterEntity;
import com.alkemy.disney.alkemylab.entity.MovieEntity;
import com.alkemy.disney.alkemylab.mapper.CharacterMapper;
import com.alkemy.disney.alkemylab.repository.CharacterRepository;
import com.alkemy.disney.alkemylab.repository.specification.CharacterSpecification;
import com.alkemy.disney.alkemylab.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterSpecification characterSpecification;

    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved);
        return result;
    }

    @Override
    public List<CharacterDTO> getAllCharacters() {
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterDTO> result = characterMapper.characterEntity2DTOList(entities);
        return result;
    }




    public List<CharacterDTO> getByFilters(String name, Integer age, Integer weight, Set<Long> movies, String order) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, movies, order);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        List<CharacterDTO> dtos = characterMapper.characterEntitySet2DTOList(entities);
        return dtos;
    }

}
