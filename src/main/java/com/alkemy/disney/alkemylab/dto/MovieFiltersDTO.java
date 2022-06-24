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
    private Long characterId;
    private Long genreId;

    public MovieFiltersDTO(String tittle, Integer rating, String order, Long characterId, Long genreId) {
        this.tittle = tittle;
        this.rating = rating;
        this.characterId = characterId;
        this.genreId = genreId;
        this.order = order;
    }

    public boolean isASC() { return order.compareToIgnoreCase(("ASC"))==0;}
    public boolean isDESC() { return order.compareToIgnoreCase(("DESC"))==0;}

}
