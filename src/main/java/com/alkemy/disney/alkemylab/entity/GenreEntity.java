package com.alkemy.disney.alkemylab.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "genres")
@Data
@SQLDelete(sql = "UPDATE genres SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class GenreEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private boolean deleted;
    @ManyToMany(cascade = CascadeType.ALL,  targetEntity = MovieEntity.class)
    @JoinTable(
            name = "movies_genres",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<MovieEntity> movies = new ArrayList<>();
}
