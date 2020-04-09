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

    public List<Movie>getAllMoviesByTitle(String title) {

        if (!title.isEmpty()) {
            return this.movieRepository.findAll();

        } else {
            return null;
        }

    }


    public boolean deleteMovieById(Long id) {
        try {
             movieRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }



    public List <Movie> addRatingStarForMovie(List<Object> movie) {
        return movieRepository.findAll();
     }

    public Movie findMovieByImdbId(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId);
    }


//    public List<Movie> searchMovieByActor_ByDirector_ByGenre(String searchActor) {
//        return this.movieRepository.searchMovieByActor_ByDirector_ByGenre(searchActor);
//    }




//    public Movie getMovieByIMDBID( String imdbid) {
//        Optional<Movie> movie = movieRepository.getOneMovieByIMDBID(imdbid);
//        return movie.orElse(null);
//    }




}
