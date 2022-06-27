package com.alkemy.disney.alkemylab.dto.movie;

import com.alkemy.disney.alkemylab.dto.character.CharacterBasicDTO;
import com.alkemy.disney.alkemylab.dto.genre.GenreBasicDTO;
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
    private List<CharacterBasicDTO> characters;
    private List<GenreBasicDTO> genres;
}
