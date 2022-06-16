package com.alkemy.disney.alkemylab.dto;

import com.alkemy.disney.alkemylab.entity.MovieEntity;
import lombok.Data;

import java.util.Set;

@Data
public class GenreDTO {
    private Long id;
    private String name;
    private String image;
    private Set<MovieEntity> movies;
}
