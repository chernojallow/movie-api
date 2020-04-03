package com.galvanize.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name ="movies")
public class Movie {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Long movieId;
@Column(unique=true)
private String  imdbId;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "actors", nullable = false)
//    @JsonIgnore
//private List<String> actors;

//    private String actors;

@Column
private String directors;
@Column
private String title;
@Column
private String year;
@Column
private String released;



      public Movie(){};


    public Movie(Long movieId, String imdbId, String directors, String title, String year, String released) {
        this.movieId = movieId;
        this.imdbId = imdbId;
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

//    public List<String> getActors() {
//        return actors;
//    }

//    public void setActors(List<String> actors) {
//        this.actors = actors;
//    }

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
//                ", actors=" + actors +
                ", directors='" + directors + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", released=" + released +
                '}';
    }
}
