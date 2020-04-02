package com.galvanize.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.Movie;
import com.galvanize.MovieService.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    MockMvc mockMvc;



  @MockBean
  MovieService movieService;


    @Test
    void createMovieTest() throws Exception {


//        String date = "16-Aug-2016";
//        LocalDate released = LocalDate.parse(date);


        List<String> actors = new ArrayList<>();
            actors.add("larry");
            actors.add("kay");
            actors.add("rob");


        //setup
        Movie movie = new Movie(1L, "imdbid", actors, "sam", "lets make a movie", "2020","2020-10-10" );

        ObjectMapper mapper = new ObjectMapper();

        String newMapper = mapper.writeValueAsString(movie);

        when(movieService.createMovie(ArgumentMatchers.any(Movie.class))).thenReturn(movie);
        mockMvc.perform(post("/api/movies").content(newMapper).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                        .andExpect(jsonPath("$.movieId").value(movie.getMovieId()))
                .andExpect(jsonPath("$.imdbId").value(movie.getImdbId()))
                .andExpect(jsonPath("$.actors").value(movie.getActors()));




    }
}