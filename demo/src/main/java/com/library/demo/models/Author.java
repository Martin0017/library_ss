package com.library.demo.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "Authors")
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idAuthor")
  private Long idAuthor;

  @NotEmpty
  @Column(name = "nameAuthor")
  private String nameAuthor;

  @NotEmpty
  @Column(name = "lastnameAuthor")
  private String lastnameAuthor;

  @JsonIgnore
  @OneToMany(mappedBy = "author", orphanRemoval = true, fetch = FetchType.EAGER)
  private Set<Book> book = new HashSet<>();

  public Author(Long idAuthor, @NotEmpty String nameAuthor, @NotEmpty String lastnameAuthor, Set<Book> book) {
    this.idAuthor = idAuthor;
    this.nameAuthor = nameAuthor;
    this.lastnameAuthor = lastnameAuthor;
    this.book = book;
  }

  public Author() {
  }

  public Long getIdAuthor() {
    return idAuthor;
  }

  public void setIdAuthor(Long idAuthor) {
    this.idAuthor = idAuthor;
  }

  public String getNameAuthor() {
    return nameAuthor;
  }

  public void setNameAuthor(String nameAuthor) {
    this.nameAuthor = nameAuthor;
  }

  public String getLastnameAuthor() {
    return lastnameAuthor;
  }

  public void setLastnameAuthor(String lastnameAuthor) {
    this.lastnameAuthor = lastnameAuthor;
  }

  public Set<Book> getBook() {
    return book;
  }

  public void setBook(Set<Book> book) {
    this.book = book;
  }

}