package com.alkemy.disney.alkemylab.repository;

import com.alkemy.disney.alkemylab.dto.MovieDTO;
import com.alkemy.disney.alkemylab.entity.CharacterEntity;
import com.alkemy.disney.alkemylab.entity.GenreEntity;
import com.alkemy.disney.alkemylab.entity.MovieCharacterEntity;
import com.alkemy.disney.alkemylab.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieCharacterRepository extends JpaRepository<MovieCharacterEntity, Long> {
    @Modifying
    @Query("delete from MovieCharacterEntity mc where mc.characterId = :id ")
    public void deleteCharacter(Long id);

    //TODO @Query("select MovieEntity from MovieCharacterEntity mc where mc.characterId = :id ")
    //List<MovieEntity> loadMovies2Character(Long id);

    @Modifying
    @Query("delete from MovieCharacterEntity mc where mc.movieId = :id")
    void deleteMovie(Long id);

    //TODO QUERY
    //List<CharacterEntity> loadCharacters2Movie(MovieDTO movie);
}
