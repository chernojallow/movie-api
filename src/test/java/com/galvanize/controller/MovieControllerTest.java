package com.galvanize.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.entity.Movie;
import com.galvanize.services.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    MockMvc mockMvc;


  @MockBean
  MovieService movieService;




    final String baseUrl = "/api/movies";

    ObjectMapper mapper = new ObjectMapper();



    //POST: add a movie to the database
    @Test
    void createMovieTest() throws Exception {


//        String date = "16-Aug-2016";
//        LocalDate released = LocalDate.parse(date);

        List<String> actors = new ArrayList<>();
            actors.add("larry");
            actors.add("kay");
            actors.add("rob");


        //setup
        Movie movie = new Movie(1L, "imdbid",  "sam", "lets make a movie", "2020","2020-10-10" );

        ObjectMapper mapper = new ObjectMapper();

        String newMapper = mapper.writeValueAsString(movie);

        when(movieService.createMovie(ArgumentMatchers.any(Movie.class))).thenReturn(movie);
        mockMvc.perform(post(baseUrl).content(newMapper).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                        .andDo(print())
                        .andExpect(jsonPath("$.movieId").value(movie.getMovieId()))
                .andExpect(jsonPath("$.imdbId").value(movie.getImdbId()));

    }


    //GET: all movies in the database
    @Test
    void getAllMoviesTest() throws Exception {

        //Set Up
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
           movies.add(movie);

           //Exercise
           when(movieService.getAllMovies()).thenReturn(movies);
        mockMvc.perform(get(baseUrl).accept(MediaType.APPLICATION_JSON))
                   .andExpect(status().isOk())
                   .andExpect(jsonPath("$", hasSize(movies.size())));

    }


   // GET: one movie by imdbid
    @Test
    public void getOneMovieByimdbId() throws Exception {
        Movie expected = new Movie();
        expected.setMovieId(1L);
        when(movieService.findMovieByImdbId("tt0241")).thenReturn(expected);
        mockMvc.perform(get(baseUrl + "/imdbId/tt0241"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.imdbId").value(expected.getImdbId()))
                .andExpect(jsonPath("$.movieId").value(expected.getMovieId()));
    }


    //GET: all movies by Title
    @Test
    void getAllMoviesByTitle_Test() throws Exception {

        //Set up
    List<Movie> movies = new ArrayList<>();
      Movie movie = new Movie();
         movie.getTitle();
         movies.add(movie);

         //Exercise
     when(movieService.getAllMoviesByTitle(movie.getTitle())).thenReturn(movies);
     mockMvc.perform(get(baseUrl).accept(MediaType.APPLICATION_JSON))
             .andExpect(status().isOk());
//             .andExpect(jsonPath("$", hasSize(movies.size())));

 }

//    PATCH: add or update a star rating for a movie (1 - 5)

 @Test
    void addStarRating_For_Movie_Test() throws Exception {

        //Set up
     Movie movie = new Movie();
     String starRating = "*";

     List<Object> movies = new ArrayList<>();
     movies.add(movie);
     movies.add("rating" + starRating);

     when(movieService.addRatingStarForMovie(movies)).thenReturn(Collections.singletonList(movie));

     //Exercise
     mockMvc.perform(get(baseUrl+ "/addMovie").accept(MediaType.APPLICATION_JSON))
             .andExpect(status().isOk())
     .andExpect(jsonPath("$", hasSize(0)));


 }


 ////    DELETE: delete a movie by id

    @Test
    void deleteMovieById_whenExist() throws Exception {
        // Setup
        when(movieService.deleteMovieById(ArgumentMatchers.any(Long.class))).thenReturn(true);

        // Exercise
        mockMvc.perform(delete(baseUrl+ "/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }



}
