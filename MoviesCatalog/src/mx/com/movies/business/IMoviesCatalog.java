package mx.com.movies.business;
import mx.com.movies.exceptions.DataReadingEx;
import mx.com.movies.exceptions.DataWritingEx;

public interface IMoviesCatalog {
    String FILE_NAME = "movies.txt";
    void addMovie(String movieName)  throws DataWritingEx;
    void listMovies() throws DataReadingEx;
    void searchMovie(String movieToSearch) throws DataReadingEx;
    void startFile() throws DataWritingEx;
}
