package com.alkemy.disney.alkemylab.dto;

import com.alkemy.disney.alkemylab.entity.MovieEntity;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CharacterFiltersDTO {
    private String name;
    private Integer age;
    private Integer weight;
    Set<Long> movies;
    private String order;

    public CharacterFiltersDTO(String name, Integer age, Integer weight, Set<Long> movies, String order) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.movies = movies;
        this.order = order;
    }

    public boolean isASC() { return order.compareToIgnoreCase("ASC") == 0;}
    public boolean isDESC() { return order.compareToIgnoreCase("DESC") == 0;}
}
