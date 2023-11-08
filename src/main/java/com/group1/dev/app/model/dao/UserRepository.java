package com.group1.dev.app.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.group1.dev.app.model.entity.EntityUser;

public interface UserRepository extends UserRepositoryCustom, CrudRepository<EntityUser,Integer>, JpaRepository<EntityUser, Integer> {
    Optional<EntityUser> findByUsername(String username);

}