package com.alkemy.disney.alkemylab.mapper;

import com.alkemy.disney.alkemylab.dto.movie.MovieDTO;
import com.alkemy.disney.alkemylab.entity.MovieEntity;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
@Mapper
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    List<MovieDTO> movieEntity2DTOList(List<MovieEntity> entities);

    //@InheritInverseConfiguration
    MovieEntity movieDTO2Entity(MovieDTO dto);


    MovieDTO movieEntity2DTO(MovieEntity entitySaved);
}
