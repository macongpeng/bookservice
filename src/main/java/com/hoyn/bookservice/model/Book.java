package com.hoyn.bookservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String title;

    @Column(name = "page_count")
    private Integer pageCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    /*
    // Constructor
    public Book() {}

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Integer getPageCount() { return pageCount; }
    public Author getAuthor() { return author; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setPageCount(Integer pageCount) { this.pageCount = pageCount; }
    public void setAuthor(Author author) { this.author = author; }
    */
}