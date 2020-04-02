package com.galvanize.services;


import com.galvanize.entity.Movie;
import com.galvanize.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class MovieServiceTest {

    @Autowired
    MovieRepository movieRepository;

    MovieService movieService;

    @BeforeEach
    void setup() {
        movieService = new MovieService(movieRepository);
    }

    @Test
    void createMovieTest() {
        // Setup

        List<String> actors = new ArrayList<>();
        actors.add("larry");
        actors.add("kay");
        actors.add("rob");

        Movie movie = new Movie(1L, "imdbid", "sam", "lets make a movie", "2020","2020-10-10" );

        // Exercise
        Movie savedMovie = movieService.createMovie(movie);

        // Assert
        assertNotNull(savedMovie.getMovieId());

        // Teardown
        movieRepository.deleteAll();
    }


    @Test
    void getAllMoviesTest(){

        //Set up
        List<Movie> movie = new ArrayList<>();
        List<Movie> movies = movieService.getAllMovies();

        //Execise
        assertEquals(movie.size(), movies.size());
        assertTrue(movie.containsAll(movies));


    }



    }




