package com.galvanize.MovieService;

import com.galvanize.Movie;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    public Movie createMovie(Movie movie) {
        return movie;
    }
}
