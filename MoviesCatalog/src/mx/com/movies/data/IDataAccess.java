package mx.com.movies.data;

import java.util.List;
import mx.com.movies.domain.Movie;
import mx.com.movies.exceptions.DataAccessEx;
import mx.com.movies.exceptions.DataReadingEx;
import mx.com.movies.exceptions.DataWritingEx;

public interface IDataAccess {
    boolean fileExists(String fileName) throws DataAccessEx;

    List<Movie> listFile(String fileName) throws DataReadingEx;

    void writeData(Movie movie, String fileName, boolean append) throws DataWritingEx;

    String searchFile(String fileName, String search) throws DataReadingEx;

    void createFile(String fileName) throws DataWritingEx;

    void eraseFile(String fileName) throws DataWritingEx;
}
