package com.alkemy.disney.alkemylab.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;


@Data
public class MovieDTO {
    private Long id;
    private String image;
    private String tittle;
    private LocalDate creationDate;
    private Integer rating;
    private List<CharacterDTO> characters;
    private List<GenreDTO> genres;
}
