package com.alkemy.disney.alkemylab.mapper;

import com.alkemy.disney.alkemylab.dto.CharacterDTO;
import com.alkemy.disney.alkemylab.entity.CharacterEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    public CharacterEntity characterDTO2Entity(CharacterDTO dto) {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setImage(dto.getImage());
        characterEntity.setName(dto.getName());
        characterEntity.setAge(dto.getAge());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setBackground(dto.getBackground());
        characterEntity.setMovieEntities(dto.getMovieEntities());
        return characterEntity;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity entity) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setBackground(entity.getBackground());
        dto.setMovieEntities(entity.getMovieEntities());
        return dto;
    }

    public List<CharacterDTO> characterEntity2DTOList(List<CharacterEntity> entities) {
        List<CharacterDTO> dtos = new ArrayList<>();
        entities.stream().forEach(entity -> dtos.add(characterEntity2DTO(entity)));
        return dtos;
    }
}
