package com.alkemy.disney.alkemylab.repository;

import com.alkemy.disney.alkemylab.entity.GenreMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreMovieRepository extends JpaRepository<GenreMovieEntity, Long> {
}
