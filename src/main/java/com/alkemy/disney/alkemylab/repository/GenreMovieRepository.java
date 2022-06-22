package com.alkemy.disney.alkemylab.repository;

import com.alkemy.disney.alkemylab.dto.GenreDTO;
import com.alkemy.disney.alkemylab.dto.MovieDTO;
import com.alkemy.disney.alkemylab.entity.GenreEntity;
import com.alkemy.disney.alkemylab.entity.GenreMovieEntity;
import com.alkemy.disney.alkemylab.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreMovieRepository extends JpaRepository<GenreMovieEntity, Long> {

    @Query("select m from MovieEntity m join GenreMovieEntity  gm on gm.movieId = m.id where gm.genreId=:id")
    List<MovieEntity> loadMovies2Genre(Long id);

    @Query("select g from GenreEntity g join GenreMovieEntity gm on gm.genreId = g.id where gm.movieId =:id")
    List<GenreEntity> loadGenres2Movie(Long id);
}
