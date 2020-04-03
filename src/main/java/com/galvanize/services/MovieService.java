package com.galvanize.services;


import com.galvanize.entity.Movie;
import com.galvanize.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    MovieRepository movieRepository;

    public MovieService(MovieRepository repository) {
        this.movieRepository = repository;
    }

   public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }


    public List<Movie> getAllMovies() {
        return this.movieRepository.findAll();
    }

//    public Movie getOneMovieByIMDBID( String imdbid) {
//        Optional<Movie> movie = movieRepository.getOneMovieByIMDBID(imdbid);
//        return movie.orElse(null);
//    }


}
