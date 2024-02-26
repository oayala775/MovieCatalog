package mx.com.movies.data;

import java.util.ArrayList;
import java.util.List;
import mx.com.movies.domain.Movie;
import mx.com.movies.exceptions.DataAccessEx;
import mx.com.movies.exceptions.DataReadingEx;
import mx.com.movies.exceptions.DataWritingEx;

import java.io.*;

// import mx.com.movies.exceptions.DataAccessEx;

public class DataAccessImplementation implements IDataAccess {
    @Override
    public boolean fileExists(String fileName) throws DataAccessEx {
        File file = new File(fileName);
        boolean isFile = file.isFile() ? true : false;
        return isFile;
    }

    @Override
    public List<Movie> listFile(String fileName) throws DataReadingEx {
        File file = new File(fileName);
        List<Movie> movieList = new ArrayList<>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String readInfo = input.readLine();
            while (readInfo != null) {
                movieList.add(new Movie(readInfo));
                readInfo = input.readLine();
            }
            input.close();
            return movieList;
        } catch (FileNotFoundException e) {
            System.out.println("Exception occurred: " + e.getMessage());
            throw new DataReadingEx("Listing not successful");
        } catch (IOException e) {
            System.out.println("Exception occurred: " + e.getMessage());
            throw new DataReadingEx("Listing not successful");
        }
    }

    @Override
    public void writeData(Movie movie, String fileName, boolean append) throws DataWritingEx {
        File file = new File(fileName);
        try {
            PrintWriter output = new PrintWriter(new FileWriter(file, append));
            output.write(movie.toString());
            output.write("\n");
            output.close();
            System.out.println("File successfully written");
        } catch (FileNotFoundException e) {
            System.out.println("Exception occurred: " + e.getMessage());
            throw new DataWritingEx("Writing not successful");
        } catch (IOException e) {
            System.out.println("Exception occurred: " + e.getMessage());
            throw new DataWritingEx("Writing not successful");
        }
    }

    @Override
    public String searchFile(String fileName, String movieToSearch) throws DataReadingEx {
        File file = new File(fileName);
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String readInfo = input.readLine();
            int index = 1;
            while (readInfo != null) {
                if (readInfo.equalsIgnoreCase(movieToSearch)) {
                    return movieToSearch + " found in line: " + index;
                }
                ++index;
                readInfo = input.readLine();
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception occurred: " + e.getMessage());
            throw new DataReadingEx("Searching not successful");
        } catch (IOException e) {
            System.out.println("Exception occurred: " + e.getMessage());
            throw new DataReadingEx("Searching not successful");
        }
        return "Movie not found";
    }

    @Override
    public void createFile(String fileName) throws DataWritingEx {
        File file = new File(fileName);
        try {
            PrintWriter output = new PrintWriter(file);
            output.close();
            System.out.println("File created successfully");
        } catch (Exception ex) {
            System.out.println("Couldn't create file " + ex.getMessage());
            throw new DataWritingEx("Creating file wasn't successful");
        }
    }

    @Override
    public void eraseFile(String fileName) throws DataWritingEx {
        File file = new File(fileName);
        if (file.exists()){
            file.delete();
            System.out.println("File content successfully erased");
        }
        else{
            System.out.println("File doesn't exists");
        }
    }
}
