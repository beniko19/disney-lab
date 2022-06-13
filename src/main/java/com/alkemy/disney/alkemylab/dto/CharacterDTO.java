package com.alkemy.disney.alkemylab.dto;

import com.alkemy.disney.alkemylab.entity.MovieEntity;
import lombok.Data;

import java.util.List;

@Data
public class CharacterDTO {
    private Long id;
    private String image;
    private String name;
    private int age;
    private int weight;
    private String background;
    private List<MovieEntity> movies;
}
