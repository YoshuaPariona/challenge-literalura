package com.challenge.literalura.main;

import com.challenge.literalura.models.Author;
import com.challenge.literalura.models.Book;
import com.challenge.literalura.models.DataAuthor;
import com.challenge.literalura.models.DataBook;
import com.challenge.literalura.repositories.AuthorRepository;
import com.challenge.literalura.repositories.BookRepository;
import com.challenge.literalura.services.ApiService;
import com.challenge.literalura.services.ConvertDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Main {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private Scanner userInput = new Scanner(System.in);
    private ApiService apiService = new ApiService();
    private ConvertDataService convertDataService = new ConvertDataService();
    private DataBook dataBook;
    private Book book;
    private List<DataAuthor> listDataAuthors;
    private List<Author> listAuthors;
    private Author author;

    @Autowired
    public Main(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        String menu = """
            ======== Bienvenido al menú de Literalura! ========
    
            1) Buscar y registrar un libro según su título
            2) Mostrar libros registrados
            3) Mostrar autores registrados
            4) Mostrar autores vivos de un año determinado
            5) Mostrar libros según su idioma
            
            0) Salir
            
            Elija una opción para continuar:
            """;
        int option = -1;

        while(option != 0) {
            System.out.println(menu);
            option = userInput.nextInt();
            userInput.nextLine();

            switch (option) {
                case 1:
                    showAndRegisterBookByTitle();
                    break;
                case 2:
                    showAllBooks();
                    break;
                case 3:
                    showAllAuthors();
                    break;
                case 4:
                    showAuthorsInYear();
                    break;
                case 5:
                    showBooksByLanguage();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación . . .");
                    break;
                default:
                    System.out.println("Error... Ingrese una opción válida");
                    break;
            }

            if(option > 0 && option < 6) {
                pauseConsole();
            }
        }
    }

    private void pauseConsole() {
        System.out.println("Presiona Enter para continuar . . .");
        userInput.nextLine();
    }
    private void showAndRegisterBookByTitle() {
        System.out.println("Ingresa el nombre del libro que desea buscar: ");
        String searchInput = userInput.nextLine();

        if(bookRepository.findByTitleContainsIgnoreCase(searchInput).isPresent()) {
            System.out.println("Este libro ya está registrado en la base de datos de literalura.");
            return;
        }

        String json = apiService.getJson(searchInput);

        listDataAuthors = convertDataService.getDataAuthor(json);
        dataBook = convertDataService.getDataBook(json);

        listAuthors = listDataAuthors.stream()
                .map(Author::new)
                .map(this::checkDuplicateAuthor)
                .collect(Collectors.toList());

        book = new Book(dataBook, listAuthors);
        bookRepository.save(book);
        System.out.println(book.toString());
    }

    private Author checkDuplicateAuthor(Author author) {
        return authorRepository.findByNameContainsIgnoreCase(author.getName())
                .orElseGet(() -> authorRepository.save(author));
    }

    private void showAllBooks() {
        System.out.println("En desarrollo");
    }

    private void showAllAuthors() {
        System.out.println("En desarrollo");
    }

    private void showAuthorsInYear() {
        System.out.println("En desarrollo");
    }

    private void showBooksByLanguage() {
        System.out.println("En desarrollo");
    }

}
