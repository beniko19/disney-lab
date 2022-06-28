package com.alkemy.disney.alkemylab.dto.movie;

import com.alkemy.disney.alkemylab.entity.CharacterEntity;
import com.alkemy.disney.alkemylab.entity.GenreEntity;
import lombok.Data;

import java.util.List;

@Data
public class MovieFiltersDTO {

    private String tittle;
    private String order;
    private List<Long> genres;

    public MovieFiltersDTO(String tittle, String order, List<Long> genresId) {
        this.tittle = tittle;
        this.genres = genresId;
        this.order = order;
    }

    public boolean isASC() { return order.compareToIgnoreCase(("ASC"))==0;}
    public boolean isDESC() { return order.compareToIgnoreCase(("DESC"))==0;}

}
