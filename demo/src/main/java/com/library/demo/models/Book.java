package com.library.demo.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBook")
    private Long idBook;

    public Book() {
    }

    @NotEmpty
    @Column(name = "nameBook")
    private String nameBook;

    @NotEmpty
    @Column(name = "genreBook")
    private String genreBook;

    @NotEmpty
    @Column(name = "volume")
    private String volumeBook;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "idAuthor")
    private Long idAuthor;

    public Set<UserHasBooks> getUserHasBooks() {
        return userHasBooks;
    }

    public void setUserHasBooks(Set<UserHasBooks> userHasBooks) {
        this.userHasBooks = userHasBooks;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "book", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UserHasBooks> userHasBooks = new HashSet<>();

    public Book(Long idBook, @NotEmpty String nameBook, @NotEmpty String genreBook, @NotEmpty String volumeBook,
            Long idAuthor, Author author) {
        this.idBook = idBook;
        this.nameBook = nameBook;
        this.genreBook = genreBook;
        this.volumeBook = volumeBook;
        this.idAuthor = idAuthor;
        this.author = author;
    }

    public Long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
    }

    @JoinColumn(name = "author")
    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getGenreBook() {
        return genreBook;
    }

    public void setGenreBook(String genreBook) {
        this.genreBook = genreBook;
    }

    public String getVolumeBook() {
        return volumeBook;
    }

    public void setVolumeBook(String volumeBook) {
        this.volumeBook = volumeBook;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author2) {
        this.author = author2;
    }
}