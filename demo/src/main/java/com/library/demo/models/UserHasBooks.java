package com.library.demo.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Table(name = "UsersHasBooks")
public class UserHasBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUserHasBook")
    private Long idUserHasBook;

    @JoinColumn(name = "user")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "idUser")
    private Long idUser;

    @JoinColumn(name = "book")
    @ManyToOne( fetch = FetchType.EAGER)
    private Book book;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "idBook")
    private Long idBook;

    @Temporal(TemporalType.DATE)
    @Column(name = "loanDate")
    private Date loanDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "returnDate")
    private Date returnDate;

    public UserHasBooks() {
    }

    public UserHasBooks(Long idUserHasBook, User user, Long idUser, Book book, Long idBook, Date loanDate,
            Date returnDate) {
        this.idUserHasBook = idUserHasBook;
        this.user = user;
        this.idUser = idUser;
        this.book = book;
        this.idBook = idBook;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public Long getIdUserHasBook() {
        return idUserHasBook;
    }

    public void setIdUserHasBook(Long idUserHasBook) {
        this.idUserHasBook = idUserHasBook;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    

}