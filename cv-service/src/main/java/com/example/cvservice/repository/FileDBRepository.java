package com.example.cvservice.repository;

import com.example.cvservice.model.FileDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
    @Query("select u from FileDB u where  u.id = ?1")
    FileDB loadById(String id);

    @Query("select u from FileDB u where  u.username = ?1")
    List<FileDB> loadByUsernam(String username);
}
