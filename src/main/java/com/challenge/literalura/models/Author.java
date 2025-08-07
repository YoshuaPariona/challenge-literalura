package com.challenge.literalura.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    //NOMBRE
    @Column(unique = true)
    private String name;

    //AÑO DE NACIMIENTO
    private Long birthYear;

    //AÑO DE FALLECIMIENTO
    private Long deathYear;

    //RELACIÓN MUCHOS A MUCHOS
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    //CONSTRUCTORES
    public Author() {}

    public Author(DataAuthor dataAuthor) {
        this.name = dataAuthor.name();
        this.birthYear = dataAuthor.birthYear();
        this.deathYear = dataAuthor.deathYear();
    }

    public Author(DataAuthor dataAuthor, List<Book> books) {
        this.name = dataAuthor.name();
        this.birthYear = dataAuthor.birthYear();
        this.deathYear = dataAuthor.deathYear();
        this.books = books;
    }

    //SETTERS Y GETTERS

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Long birthYear) {
        this.birthYear = birthYear;
    }

    public Long getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Long deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {

        String bookList = books.stream()
                .map(Book::getTitle)
                .reduce((a1, a2) -> a1 + " -*- " + a2)
                .orElse("N/A");

        return "\n" + "======== AUTOR ========" + "\n" +
                "Apellidos y nombres: " + name + "\n" +
                "Año de nacimiento: " + birthYear + "\n" +
                "Año de fallecimiento: " + deathYear + "\n" +
                "Libros escritos: " + bookList + "\n" +
                "===================";
    }
}
