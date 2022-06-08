package com.alkemy.disney.alkemylab.entidad;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "character")
@Data
public class Character implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;
    private int age;
    private int weight;
    private String background;
    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
    private List<Movie> movies;
}
