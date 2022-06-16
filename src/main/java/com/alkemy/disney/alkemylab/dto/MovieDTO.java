package com.alkemy.disney.alkemylab.dto;

import com.alkemy.disney.alkemylab.entity.CharacterEntity;
import com.alkemy.disney.alkemylab.entity.GenreEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class MovieDTO {
    private Long id;
    private String image;
    private String tittle;
    private LocalDate creationDate;
    private Integer rating;
    private List<CharacterEntity> characters;
    private List<GenreEntity> genres;
}
