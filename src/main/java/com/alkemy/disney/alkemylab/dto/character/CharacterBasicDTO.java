package com.alkemy.disney.alkemylab.dto.character;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CharacterBasicDTO {
    private Long id;
    private String image;
    private String name;
    private int age;
    private int weight;
    private String background;
}
