package com.alkemy.disney.alkemylab.dto;

import lombok.Data;
import java.util.List;

@Data
public class GenreDTO {
    private Long id;
    private String name;
    private String image;
    private List<MovieDTO> movies;
}
