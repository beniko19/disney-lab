package com.alkemy.disney.alkemylab.repository;

import com.alkemy.disney.alkemylab.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    List<MovieEntity> findAll(Specification<MovieEntity> spec);
    @Modifying
    @Query("UPDATE MovieEntity m SET m.deleted=true where m.id = :id")
    void deleteByIdMovie(Long id);
}
