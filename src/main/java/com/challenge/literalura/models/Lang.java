package com.challenge.literalura.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "lang")
public class Lang {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long langId;

    //CÓDIGO
    @Column(unique = true)
    private String langCode;

    //RELACIÓN MUCHOS A MUCHOS
    @ManyToMany(mappedBy = "langs")
    private List<Book> books;

    //CONSTRUCTORES
    public Lang() {}

    public Lang(String langCode) {
        this.langCode = langCode;
    }

    //SETTERS Y GETTERS

    public Long getLanguageId() {
        return langId;
    }

    public void setLanguageId(Long langId) {
        this.langId = langId;
    }

    public String getLanguageCode() {
        return langCode;
    }

    public void setLanguageCode(String langCode) {
        this.langCode = langCode;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
