package com.alkemy.disney.alkemylab.repository;

import com.alkemy.disney.alkemylab.entity.CharacterEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    List<CharacterEntity> findAll(Specification<CharacterEntity> spec);
    @Modifying
    @Query("UPDATE CharacterEntity c SET c.deleted=true where c.id = :id")
    void deleteByIdCharacter(Long id);
}
