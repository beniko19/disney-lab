package com.alkemy.disney.alkemylab.auth.repository;

import com.alkemy.disney.alkemylab.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
