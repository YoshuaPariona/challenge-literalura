package com.challenge.literalura.services;

import com.challenge.literalura.models.Author;
import com.challenge.literalura.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author checkDuplicateAuthor(Author author) {
        return authorRepository.findByNameContainsIgnoreCase(author.getName())
                .orElseGet(() -> authorRepository.save(author));
    }
}
