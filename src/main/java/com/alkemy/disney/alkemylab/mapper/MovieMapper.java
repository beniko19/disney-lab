package com.alkemy.disney.alkemylab.mapper;

import com.alkemy.disney.alkemylab.dto.MovieDTO;
import com.alkemy.disney.alkemylab.entity.MovieEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    public MovieEntity movieDTO2Entity(MovieDTO dto) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setImage(dto.getImage());
        movieEntity.setTittle(dto.getTittle());
        movieEntity.setCreationDate(dto.getCreationDate());
        movieEntity.setRating(dto.getRating());
        movieEntity.setCharacters(dto.getCharacters());
        return movieEntity;
    }

    public MovieDTO movieEntity2DTO(MovieEntity entity) {
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTittle(entity.getTittle());
        dto.setCreationDate(entity.getCreationDate());
        dto.setRating(entity.getRating());
        dto.setCharacters(entity.getCharacters());
        return dto;
    }

    public List<MovieDTO> movieEntity2DTOList(List<MovieEntity> entities) {
        List<MovieDTO> dtos = new ArrayList<>();
        entities.stream().forEach(entity -> dtos.add(movieEntity2DTO(entity)));
        return dtos;
    }
}
