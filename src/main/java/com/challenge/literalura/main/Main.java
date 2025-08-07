package com.challenge.literalura.main;

import com.challenge.literalura.models.*;
import com.challenge.literalura.repositories.AuthorRepository;
import com.challenge.literalura.repositories.BookRepository;
import com.challenge.literalura.repositories.LangRepository;
import com.challenge.literalura.services.ApiService;
import com.challenge.literalura.services.BookService;
import com.challenge.literalura.services.ConvertDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Main {

    @Autowired
    private BookService bookService;

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LangRepository langRepository;
    private Scanner userInput = new Scanner(System.in);


    @Autowired
    public Main(BookRepository bookRepository, AuthorRepository authorRepository, LangRepository langRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.langRepository = langRepository;
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
                    showBooksByLang();
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

        if (bookService.findBookByTitle(searchInput).isPresent()) {
            System.out.println("Este libro ya está registrado.");
            return;
        }

        Book book = bookService.fetchAndSaveBook(searchInput);
        System.out.println(book);
    }

    private void showAllBooks() {
        List<Book> books = bookService.getAllBooksWithAuthorsAndLangs();
        books.forEach(System.out::println);
    }

    private void showAllAuthors() {
        System.out.println("En desarrollo");
    }

    private void showAuthorsInYear() {
        System.out.println("En desarrollo");
    }

    private void showBooksByLang() {
        System.out.println("En desarrollo");
    }

}
