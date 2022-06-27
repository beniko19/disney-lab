package com.alkemy.disney.alkemylab.dto.character;

import com.alkemy.disney.alkemylab.dto.movie.MovieBasicDTO;
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
    private List<MovieBasicDTO> movies;
}
