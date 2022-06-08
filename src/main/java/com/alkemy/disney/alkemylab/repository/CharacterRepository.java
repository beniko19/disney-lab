package com.alkemy.disney.alkemylab.repository;

import com.alkemy.disney.alkemylab.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> { }
