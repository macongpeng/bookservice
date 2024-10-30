package com.hoyn.bookservice.controller;

import com.hoyn.bookservice.repository.AuthorRepository;
import com.hoyn.bookservice.repository.BookRepository;
import com.hoyn.bookservice.model.Author;
import com.hoyn.bookservice.model.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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

    // New Mutation Mappings
    @MutationMapping
    public Book createBook(@Argument String title,
                        @Argument Integer pageCount,
                        @Argument Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NoSuchElementException("Author not found with id: " + authorId));

        Book book = new Book();
        book.setTitle(title);
        book.setPageCount(pageCount);
        book.setAuthor(author);

        Book savedBook = bookRepository.save(book);
        if (savedBook == null) {
            throw new RuntimeException("Failed to save book");
        }
        return savedBook;
    }

    @MutationMapping
    public Book updateBook(@Argument Long id,
                           @Argument String title,
                           @Argument Integer pageCount,
                           @Argument Long authorId) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + id));

        if (title != null) {
            book.setTitle(title);
        }
        if (pageCount != null) {
            book.setPageCount(pageCount);
        }
        if (authorId != null) {
            Author author = authorRepository.findById(authorId)
                    .orElseThrow(() -> new NoSuchElementException("Author not found with id: " + authorId));
            book.setAuthor(author);
        }
        return bookRepository.save(book);
    }

    @MutationMapping
    public Boolean deleteBook(@Argument Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + id));

        bookRepository.delete(book);
        return true;
    }
}