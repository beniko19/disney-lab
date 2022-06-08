package com.alkemy.disney.alkemylab.repository;

import com.alkemy.disney.alkemylab.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> { }
