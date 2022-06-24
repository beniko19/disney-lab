package com.alkemy.disney.alkemylab.repository;

import com.alkemy.disney.alkemylab.entity.CharacterEntity;
import com.alkemy.disney.alkemylab.entity.MovieCharacterEntity;
import com.alkemy.disney.alkemylab.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieCharacterRepository extends JpaRepository<MovieCharacterEntity, Long> {
    @Modifying
    @Query("update MovieCharacterEntity mc set mc.deleted=true where mc.characterId = :id")
    void deleteCharacter(Long id);

    @Modifying
    @Query("update MovieCharacterEntity mc set mc.deleted=true where mc.characterId = :id and mc.movieId=:movieId")
    void deleterCharacterFromMovie(Long id, Long movieId);

    @Query("select m from MovieEntity m join MovieCharacterEntity gm on gm.movieId = m.id where gm.characterId=:id and gm.deleted=false")
    List<MovieEntity> loadMovies2Character(Long id);

    @Modifying
    @Query("update MovieCharacterEntity mc set mc.deleted=true where mc.movieId = :id")
    void deleteMovie(Long id);

    @Query("select c from CharacterEntity c join MovieCharacterEntity gm on gm.characterId = c.id where gm.movieId =:id and gm.deleted=false")
    List<CharacterEntity> loadCharacters2Movie(Long id);

    @Query("select mc from MovieCharacterEntity mc where mc.characterId = :id")
    Optional<MovieCharacterEntity> findByCharacterId(Long id);

    @Query("select mc from MovieCharacterEntity mc where mc.movieId = :id")
    Optional<MovieCharacterEntity> findByMovieId(Long id);
}
