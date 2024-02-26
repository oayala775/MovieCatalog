package mx.com.movies.business;

import java.util.ArrayList;
import java.util.List;

import mx.com.movies.data.DataAccessImplementation;
import mx.com.movies.data.IDataAccess;
import mx.com.movies.domain.Movie;
// import mx.com.movies.exceptions.DataWritingEx;
import mx.com.movies.exceptions.DataReadingEx;

public class MoviesCatalogImplementation implements IMoviesCatalog {

    private final IDataAccess data;

    public MoviesCatalogImplementation(IDataAccess data) {
        this.data = data;
    }

    public MoviesCatalogImplementation() {
        this.data = new DataAccessImplementation();
    }

    @Override
    public void addMovie(String movieName) {
        try {
            if (data.fileExists(IMoviesCatalog.FILE_NAME)) {
                data.writeData(new Movie(movieName), IMoviesCatalog.FILE_NAME, true);
            } else {
                data.createFile(IMoviesCatalog.FILE_NAME);
                data.writeData(new Movie(movieName), IMoviesCatalog.FILE_NAME, true);
            }
        } catch (Exception e) {
            System.out.println("Exception found " + e.getMessage());
        }
    }

    @Override
    public void listMovies() {
        List<Movie> movieList = new ArrayList<>();
        try {
            if (data.fileExists(IMoviesCatalog.FILE_NAME)) {
                movieList = data.listFile(IMoviesCatalog.FILE_NAME);
                System.out.println(movieList.toString());
            } else {
                data.createFile(IMoviesCatalog.FILE_NAME);
                movieList = data.listFile(IMoviesCatalog.FILE_NAME);
                System.out.println(movieList.toString());
            }
        } catch (Exception e) {
            System.out.println("Exception found " + e.getMessage());
        }
        
    }
    
    @Override
    public void searchMovie(String movieToSearch) {
        try{
            if (data.fileExists(IMoviesCatalog.FILE_NAME)) {
                String isMovieFoundString = data.searchFile(IMoviesCatalog.FILE_NAME, movieToSearch);
                System.out.println(isMovieFoundString);
            } else{
                throw new DataReadingEx("File inexistent");
            }
        } catch(Exception e){
            System.out.println("Exception found " + e.getMessage());
        }
    }

    @Override
    public void startFile() {
        try{
            data.createFile(IMoviesCatalog.FILE_NAME);
        } catch(Exception e){
            System.out.println("Exception found " + e.getMessage());
        }
    }

    public IDataAccess getData() {
        return data;
    }

}
