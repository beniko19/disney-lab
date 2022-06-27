package com.alkemy.disney.alkemylab.dto.genre;

import com.alkemy.disney.alkemylab.dto.movie.MovieBasicDTO;
import lombok.Data;
import java.util.List;

@Data
public class GenreDTO {
    private Long id;
    private String name;
    private String image;
    private List<MovieBasicDTO> movies;
}
