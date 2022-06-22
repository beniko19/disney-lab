package com.alkemy.disney.alkemylab.repository;

import com.alkemy.disney.alkemylab.dto.MovieDTO;
import com.alkemy.disney.alkemylab.entity.CharacterEntity;
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

    @Query("select m from MovieEntity m join MovieCharacterEntity gm on gm.movieId = m.id where gm.characterId=:id")
    List<MovieEntity> loadMovies2Character(Long id);

    @Modifying
    @Query("delete from MovieCharacterEntity mc where mc.movieId = :id")
    void deleteMovie(Long id);

    //TODO void updateCharacterMovies(Long id, List<MovieEntity> movies);

    @Query("select c from CharacterEntity c join MovieCharacterEntity gm on gm.characterId = c.id where gm.movieId =:id")
    List<CharacterEntity> loadCharacters2Movie(Long id);
}
