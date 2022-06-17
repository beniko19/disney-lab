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
@Table(name = "movie_character")
@SQLDelete(sql = "UPDATE movie_character SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class MovieCharacterEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "character_id")
    private Long characterId;
    private boolean deleted;
}
