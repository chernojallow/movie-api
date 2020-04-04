package com.galvanize.services;


import com.galvanize.entity.Movie;
import com.galvanize.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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


        System.out.println(movie);

        //Execise
        assertEquals(movie.size(), movies.size());
        assertTrue(movie.containsAll(movies));


    }

    @Test
    void getAllMoviesByTitle_Test(){

        // set up

        List<Movie> movie = new ArrayList<>();

        String title = "Star trek";

         List<Movie> movies = movieService.getAllMoviesByTitle(title);

        //Exercise
        assertFalse(movie.contains(title));
        assertEquals(movie.size(), movies.size());
        assertTrue(movie.containsAll(movies));
    }



    @Test
    void addStarRating_For_Movie_Test(){

        //Set up
        Movie movie = new Movie();
        String starRating = "*";

        List<Object> movies = new ArrayList<>();
        movies.add(movie);
        movies.add("rating" + starRating);

        //Exercise
        List<Movie> addRatingStar = movieService.addRatingStarForMovie(movies);


        //assert
        assertNotNull(addRatingStar);
        assertTrue(movies.contains("rating" + starRating));


    }


    @Test
    void deleteMovieById_whenExist(){

        Movie movie = movieService.createMovie(new Movie(1L, "imdbid", "sam", "lets make a movie", "2020","2020-10-10" ));

        //Exercise
        boolean actual = movieService.deleteMovieById(movie.getMovieId());

         //Assert
        assertEquals(true, actual);
        assertNotEquals(actual,movie);



    }

    }




