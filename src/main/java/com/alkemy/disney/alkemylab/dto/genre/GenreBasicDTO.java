package com.alkemy.disney.alkemylab.dto.genre;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class GenreBasicDTO {
    private Long id;
    private String name;
    private String image;
}
