package com.challenge.literalura.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TÍTULO
    @Column(unique = true)
    private String title;

    //LISTA DE LENGUAJES
    @ElementCollection
    @CollectionTable(
            name = "book_languages",
            joinColumns = @JoinColumn(name = "book_id")
    )
    private List<String> languages;

    //NÚMERO DE DESCARGAS
    private Long numDownloads;

    //RELACIÓN MUCHOS A MUCHOS
    @ManyToMany
    @JoinTable(
            name = "books_authors", // tabla intermedia
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    //CONSTRUCTORES
    public Book() {}

    public Book(DataBook dataBook, List<Author> authors) {
        this.title = dataBook.title();
        this.languages = dataBook.languages();
        this.numDownloads = dataBook.numDownloads();
        this.authors = authors;
    }


    //SETTERS Y GETTERS
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Long getNumDownloads() {
        return numDownloads;
    }

    public void setNumDownloads(Long numDownloads) {
        this.numDownloads = numDownloads;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "\n" + "==== LIBRO ====" + "\n" +
                "Título: " + title + "\n" +
                "Autores: " + authors.get(0).getName() + "\n" +
                "Idiomas: " + languages.get(0) + "\n" +
                "Nümero de descargas: " + numDownloads + "\n" +
                "==============" + "\n";
    }
}
