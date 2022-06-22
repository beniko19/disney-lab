package com.alkemy.disney.alkemylab.dto;


import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CharacterFiltersDTO {
    private String name;
    private Integer age;
    private Integer weight;
    String movieName;
    private String order;

    public CharacterFiltersDTO(String name, Integer age, Integer weight, String movieName, String order) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.movieName = movieName;
        this.order = order;
    }

    public boolean isASC() { return order.compareToIgnoreCase("ASC") == 0;}
    public boolean isDESC() { return order.compareToIgnoreCase("DESC") == 0;}
}
