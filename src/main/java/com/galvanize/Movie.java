package com.galvanize;

import java.time.LocalDate;
import java.util.List;

public class Movie {



private Long movieId;
private String  imdbId;
private List<String> actors;
private String directors;
private String title;
private String year;
private String released;


      public Movie(){};


    public Movie(Long movieId, String imdbId, List<String> actors, String directors, String title, String year, String released) {
        this.movieId = movieId;
        this.imdbId = imdbId;
        this.actors = actors;
        this.directors = directors;
        this.title = title;
        this.year = year;
        this.released = released;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", imdbId='" + imdbId + '\'' +
                ", actors=" + actors +
                ", directors='" + directors + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", released=" + released +
                '}';
    }
}
