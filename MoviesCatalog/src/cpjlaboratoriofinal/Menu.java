package cpjlaboratoriofinal;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import mx.com.movies.business.MoviesCatalogImplementation;

public class Menu {
    private int option = 5;

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (option != 0) {
            System.out.println("Choose an option: ");
            System.out.println("1. Start movie catalog");
            System.out.println("2. Add a movie");
            System.out.println("3. List all movies");
            System.out.println("4. Search for a movie");
            System.out.println("0. Quit");
            System.out.printf("-> ");
            option = scanner.nextInt();
            scanner.nextLine();
            implementation(option, scanner);
            try{
                TimeUnit.SECONDS.sleep(1);
                clearScreen();
            } catch(Exception e){
                System.out.println("Exception found in adding a movie related to time");
            }
        }
        scanner.close();
        System.exit(0);
    }

    private void implementation(int option, Scanner scanner){
        MoviesCatalogImplementation movieCatalog = new MoviesCatalogImplementation();
        String movieAux;
        if (option == 1) {
            movieCatalog.startFile();
        } else if (option == 2) {
            System.out.printf("Enter the name of the movie to add: ");
            movieAux = scanner.nextLine();
            movieCatalog.addMovie(movieAux);
        } else if(option == 3){
            movieCatalog.listMovies();
        } else if(option == 4){
            System.out.printf("Enter the movie to search: ");
            movieAux = scanner.nextLine();
            movieCatalog.searchMovie(movieAux);
        } else if (option == 0) {
            clearScreen();
            
        }
    }

    private void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
