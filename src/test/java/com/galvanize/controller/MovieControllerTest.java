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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

        //setup
        Movie movie = new Movie();

        ObjectMapper mapper = new ObjectMapper();

        String newMapper = mapper.writeValueAsString(movie);

        when(movieService.createMovie(ArgumentMatchers.any(Movie.class))).thenReturn(movie);
        mockMvc.perform(post("/api/movie").content(newMapper).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());




    }
}
