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
@Table(name = "movie_character")
public class MovieCharacterEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "character_id")
    private Long characterId;
}
