package com.alkemy.disney.alkemylab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "genres_movies")
@SQLDelete(sql = "UPDATE genre_movie SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class GenreMovieEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "genre_id")
    private Long genreId;

    @Column(name = "movie_id")
    private Long movieId;

    private boolean deleted;
}
