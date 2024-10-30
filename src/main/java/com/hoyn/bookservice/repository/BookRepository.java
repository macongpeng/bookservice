package com.hoyn.bookservice.repository;

import com.hoyn.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Find books by author ID
    List<Book> findByAuthorId(Long authorId);
    
    // Find books by name containing text (case insensitive)
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Find books by id
    List<Book> findById(Integer id);
}