package com.alkemy.disney.alkemylab.mapper;

import com.alkemy.disney.alkemylab.dto.character.CharacterDTO;
import com.alkemy.disney.alkemylab.entity.CharacterEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
@Mapper()
public interface CharacterMapper {

    CharacterMapper INSTANCE =  Mappers.getMapper(CharacterMapper.class);

    CharacterEntity characterDTO2Entity(CharacterDTO dto);

    CharacterDTO characterEntity2DTO(CharacterEntity entitySaved);

    List<CharacterDTO> characterEntity2DTOList(List<CharacterEntity> entities);

}
