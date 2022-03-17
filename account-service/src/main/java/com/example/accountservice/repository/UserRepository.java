package com.example.accountservice.repository;

import com.example.accountservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Query("select u from User u where u.username = ?1")
    User loadByUsername(String username);

    @Query("select u from User u where u.email = ?1")
    User loadByEmail(String email);

    @Query("select u from User u where  u.resettoken = ?1")
    User loadByToKen(String token);
}
