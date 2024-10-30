package com.hoyn.bookservice.controller;

import com.hoyn.bookservice.repository.AuthorRepository;
import com.hoyn.bookservice.repository.BookRepository;
import com.hoyn.bookservice.model.Author;
import com.hoyn.bookservice.model.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Controller
public class BookController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    // Constructor injection instead of @Autowired
    public BookController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @QueryMapping
    public List<Book> books() {
        return bookRepository.findAll();
    }

    @QueryMapping
    public Book bookById(@Argument Integer id) {
        return bookRepository.findById(id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No book found with Id: " + id));
    }


    @SchemaMapping
    public Author author(Book book) {
        return authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new NoSuchElementException(
                        "Author not found for book: " + book.getTitle()));
    }
}