package com.challenge.literalura.main;

import com.challenge.literalura.service.ApiService;

import java.util.Scanner;

//Clase principal
public class Main {

    Scanner userInput = new Scanner(System.in);

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
        int opc = -1;

        while(opc != 0) {
            System.out.println(menu);
            opc = userInput.nextInt();

            switch (opc) {
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

        }
    }

    private void showAndRegisterBookByTitle() {
        ApiService apiService = new ApiService();
        System.out.println(apiService.getApiResponse("https://gutendex.com/books/"));
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
