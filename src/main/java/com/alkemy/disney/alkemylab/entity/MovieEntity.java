package com.alkemy.disney.alkemylab.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "movies")
@SQLDelete(sql = "UPDATE movies SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

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
    private boolean deleted;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = CharacterEntity.class)
    @JoinTable(
            name = "movies_characters",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private List<CharacterEntity> characters = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = GenreEntity.class)
    @JoinTable(
            name = "movies_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<GenreEntity> genres = new ArrayList<>();

}
