package com.hoyn.bookservice.repository;

import com.hoyn.bookservice.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

    // Find authors by name containing text (case insensitive)
    List<Author> findByNameContainingIgnoreCase(String name);

    // Find author by name exact match
    Optional<Author> findByNameIgnoreCase(String name);

    // Find author by id exact match
    Optional<Author> findById(Long id);
}
