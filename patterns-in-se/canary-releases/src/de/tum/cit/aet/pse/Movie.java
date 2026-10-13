package de.tum.cit.aet.pse;

public class Movie {
    private final String movieName;
    private final String genre;
    private final int year;

    public Movie(String movieName, String genre, int year) {
        this.movieName = movieName;
        this.genre = genre;
        this.year = year;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }
}

