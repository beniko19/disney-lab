package com.alkemy.disney.alkemylab.dto;

import com.alkemy.disney.alkemylab.entity.CharacterEntity;
import com.alkemy.disney.alkemylab.entity.GenreEntity;
import lombok.Data;

import java.util.List;

@Data
public class MovieFiltersDTO {

    private String tittle;
    private String order;
    private Integer rating;
    private String characterName;
    private String genreName;

    public MovieFiltersDTO(String tittle, Integer rating, String order, String characterName, String genreName) {
        this.tittle = tittle;
        this.rating = rating;
        this.characterName = characterName;
        this.genreName = genreName;
        this.order = order;
    }

    public boolean isASC() { return order.compareToIgnoreCase(("ASC"))==0;}
    public boolean isDESC() { return order.compareToIgnoreCase(("DESC"))==0;}

}
