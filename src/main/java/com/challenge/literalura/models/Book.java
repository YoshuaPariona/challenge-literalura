package com.challenge.literalura.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    //TÍTULO
    @Column(unique = true)
    private String title;

    //LISTA DE LENGUAJES
    @ElementCollection
    @CollectionTable(
            name = "book_language",
            joinColumns = @JoinColumn(name = "book_id")
    )
    private List<String> languages;

    //NÚMERO DE DESCARGAS
    private Long numDownloads;

    //RELACIÓN MUCHOS A MUCHOS
    @ManyToMany()
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "authorId")
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
        return bookId;
    }
    public void setId(Long id) {
        this.bookId = id;
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

        String authorList = authors.stream()
                .map(Author::getName)
                .reduce((a1, a2) -> a1 + "; " + a2)
                .orElse("N/A");

        return "\n" + "==== LIBRO ====" + "\n" +
                "Título: " + title + "\n" +
                "Autores: " + authorList + "\n" +
                "Idiomas: " + (languages.isEmpty() ? "N/A" : String.join(", ", languages)) + "\n" +
                "Número de descargas: " + numDownloads + "\n" +
                "==============" + "\n";
    }
}
