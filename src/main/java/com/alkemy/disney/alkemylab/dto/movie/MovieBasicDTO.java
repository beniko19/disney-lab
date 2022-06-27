package com.alkemy.disney.alkemylab.dto.movie;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@Data
public class MovieBasicDTO {
    private Long id;
    private String image;
    private String tittle;
    private LocalDate creationDate;
    private int rating;
}
