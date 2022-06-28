package com.alkemy.disney.alkemylab.mapper;

import com.alkemy.disney.alkemylab.dto.genre.GenreDTO;
import com.alkemy.disney.alkemylab.entity.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);


    List<GenreEntity> genreDTOList2EntityList(List<GenreDTO> genres);

    GenreEntity genreDTO2Entity(GenreDTO dto);

    GenreDTO genreEntity2DTO(GenreEntity entitySaved);

    List<GenreDTO> genreEntity2DTOList(List<GenreEntity> entities);
}
