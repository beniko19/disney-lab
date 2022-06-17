package com.alkemy.disney.alkemylab.repository;

import com.alkemy.disney.alkemylab.dto.GenreDTO;
import com.alkemy.disney.alkemylab.dto.MovieDTO;
import com.alkemy.disney.alkemylab.entity.GenreEntity;
import com.alkemy.disney.alkemylab.entity.GenreMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreMovieRepository extends JpaRepository<GenreMovieEntity, Long> {
    //TODO List<MovieDTO> loadMovies2Genre(GenreDTO result);

    //TODO List<GenreEntity> loadGenres2Movie(MovieDTO movie);
}
