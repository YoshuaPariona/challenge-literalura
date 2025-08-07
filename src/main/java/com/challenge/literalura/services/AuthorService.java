package com.challenge.literalura.services;

import com.challenge.literalura.models.Author;
import com.challenge.literalura.models.Book;
import com.challenge.literalura.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author checkDuplicateAuthor(Author author) {
        return authorRepository.findByNameContainsIgnoreCase(author.getName())
                .orElseGet(() -> authorRepository.save(author));
    }

    public List<Author> getAllAuthorsWithBooks() {
        return authorRepository.findAllAuthorsWithBooks();
    }

    public List<Author> getAuthorsLiveInYear(Long year) {
        return authorRepository.findAuthorsLiveInYear(year);
    }
}
