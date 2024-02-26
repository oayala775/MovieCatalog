package mx.com.movies.domain;

public class Movie {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Movie(String name) {
        this.name = name;
    }

    public Movie() {
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        return sb.toString();
    }
}
