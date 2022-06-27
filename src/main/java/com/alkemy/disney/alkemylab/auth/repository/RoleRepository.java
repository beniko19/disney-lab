package com.alkemy.disney.alkemylab.auth.repository;

import com.alkemy.disney.alkemylab.auth.entity.EnumRoleEntity;
import com.alkemy.disney.alkemylab.auth.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(EnumRoleEntity name);

}
