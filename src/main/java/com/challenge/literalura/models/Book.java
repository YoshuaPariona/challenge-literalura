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

    //NÚMERO DE DESCARGAS
    private Long numDownloads;

    //RELACIÓN LENGUAJE
    @ManyToMany()
    @JoinTable(
            name = "book_lang",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "lang_id", referencedColumnName = "langId")
    )
    private List<Lang> langs;

    //RELACIÓN AUTOR
    @ManyToMany()
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "authorId")
    )
    private List<Author> authors;

    //CONSTRUCTORES
    public Book() {}

    public Book(DataBook dataBook, List<Author> authors, List<Lang> langs) {
        this.title = dataBook.title();
        this.numDownloads = dataBook.numDownloads();
        this.authors = authors;
        this.langs = langs;
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

    public List<Lang> getLangs() {
        return langs;
    }

    public void setLangs(List<Lang> langs) {
        this.langs = langs;
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
                .reduce((a1, a2) -> a1 + " -*- " + a2)
                .orElse("N/A");

        String langList = langs.stream()
                .map(Lang::getLanguageCode)
                .reduce((a1, a2) -> a1 + ", " + a2)
                .orElse("N/A");

        return "\n" + "======== LIBRO ========" + "\n" +
                "Título: " + title + "\n" +
                "Autor(es): " + authorList + "\n" +
                "Idioma: " + langList + "\n" +
                "Número de descargas: " + numDownloads + "\n" +
                "==============";
    }
}
