package com.challenge.literalura.repositories;

import com.challenge.literalura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleContainsIgnoreCase(String titulo);

    @Query("SELECT b FROM Book b JOIN FETCH b.langs")
    List<Book> findAllBooksWithLangs();

    @Query("SELECT b FROM Book b JOIN FETCH b.authors")
    List<Book> findAllBooksWithAuthors();
}
