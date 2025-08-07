package com.challenge.literalura.services;

import com.challenge.literalura.models.*;
import com.challenge.literalura.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final LangService langService;
    private final ApiService apiService;
    private final ConvertDataService convertDataService;

    @Autowired
    public BookService(BookRepository bookRepository,
                       AuthorService authorService,
                       LangService langService,
                       ApiService apiService,
                       ConvertDataService convertDataService)
    {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.langService = langService;
        this.apiService = apiService;
        this.convertDataService = convertDataService;
    }

    public Optional<Book> findBookByTitle(String title) {
        return bookRepository.findByTitleContainsIgnoreCase(title);
    }

    public Book findAndRegisterBookByTitle(String title) {
        String json = apiService.getJson(title);

        DataBook dataBook = convertDataService.getDataBook(json);

        if (dataBook == null) {
            return null;
        }

        List<DataAuthor> dataAuthorsList = convertDataService.getDataAuthor(json);

        List<Lang> langs = dataBook.langs().stream()
                .map(langService::checkDuplicateLang)
                .toList();

        List<Author> authors = dataAuthorsList.stream()
                .map(Author::new)
                .map(authorService::checkDuplicateAuthor)
                .toList();

        Book book = new Book(dataBook, authors, langs);
        return bookRepository.save(book);
    }

    public List<Book> getAllBooksWithAuthorsAndLangs() {
        List<Book> booksWithAuthorsList = bookRepository.findAllBooksWithAuthors();
        List<Book> booksWithLangsList = bookRepository.findAllBooksWithLangs();

        Map<Long, Book> booksMap = booksWithAuthorsList.stream()
                .collect(Collectors.toMap(Book::getId, b -> b));

        for (Book booksWithLangs : booksWithLangsList) {
            Book target = booksMap.get(booksWithLangs.getId());
            if (target != null) {
                target.setLangs(booksWithLangs.getLangs());
            }
        }

        return new ArrayList<>(booksMap.values());
    }

    @Transactional
    public List<Book> getAllBooksWithAuthorsAndLangs(String langCode) {

        List<Book> booksWithLangsList = bookRepository.findBooksByLangWithAuthors(langCode);

        Map<Long, Book> booksMap = new HashMap<>();
        for (Book book : booksWithLangsList) {
            booksMap.put(book.getId(), book);
        }

        List<Book> booksWithAuthorsList = bookRepository.findAllBooksWithAuthors();
        for (Book bookWithAuthor : booksWithAuthorsList) {
            Book target = booksMap.get(bookWithAuthor.getId());
            if (target != null) {
                target.setAuthors(bookWithAuthor.getAuthors());
            }
        }

        List<Book> completeBooks = booksMap.values().stream()
                .filter(book -> book.getAuthors() != null && !book.getAuthors().isEmpty()
                        && book.getLangs() != null && !book.getLangs().isEmpty())
                .collect(Collectors.toList());

        return completeBooks;
    }
}
