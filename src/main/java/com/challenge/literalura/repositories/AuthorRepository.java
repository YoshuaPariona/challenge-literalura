package com.challenge.literalura.repositories;

import com.challenge.literalura.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameContainsIgnoreCase(String name);

    @Query("SELECT a FROM Author a JOIN FETCH a.books")
    List<Author> findAllAuthorsWithBooks();

    @Query("SELECT a FROM Author a JOIN FETCH a.books WHERE :year BETWEEN a.birthYear AND a.deathYear")
    List<Author> findAuthorsLiveInYear(Long year);
}

