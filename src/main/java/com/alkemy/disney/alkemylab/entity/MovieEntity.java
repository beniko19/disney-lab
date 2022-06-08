package com.alkemy.disney.alkemylab.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "movie")
public class MovieEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String tittle;
    @Column(name = "creation_date")

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate creationDate;
    private int rating;
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "movie_character",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private Set<CharacterEntity> characters;

    @ManyToMany(mappedBy = "movieEntities", cascade = CascadeType.ALL)
    private List<GenreEntity> genres = new ArrayList<>();
}
