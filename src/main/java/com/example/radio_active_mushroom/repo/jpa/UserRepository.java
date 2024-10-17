package com.example.radio_active_mushroom.repo.jpa;

import com.example.radio_active_mushroom.models.jpa.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    @Query("select u from UserEntity u where u.verification_token = ?1")
    Optional<UserEntity> findByVerification_token(String verification_token);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);
}