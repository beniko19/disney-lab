package com.alkemy.disney.alkemylab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "genre_movie")
public class GenreMovieEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "genre_id")
    private Long genreId;

    @Column(name = "movie_id")
    private Long movieId;
}
