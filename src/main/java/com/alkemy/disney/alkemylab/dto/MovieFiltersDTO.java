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
    //private List<CharacterDTO> characters;
    //private List<GenreDTO> genres;

    public MovieFiltersDTO(String tittle, Integer rating, /*List<CharacterDTO> characters,*/
                           /*List<GenreDTO> genres,*/ String order) {
        this.tittle = tittle;
        this.rating = rating;
        //this.characters = characters;
        //this.genres = genres;
        this.order = order;
    }

    public boolean isASC() { return order.compareToIgnoreCase(("ASC"))==0;}
    public boolean isDESC() { return order.compareToIgnoreCase(("DESC"))==0;}
}
