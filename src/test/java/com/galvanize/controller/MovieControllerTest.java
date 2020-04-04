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
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
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
                        .andExpect(jsonPath("$.movieId").value(movie.getMovieId()))
                .andExpect(jsonPath("$.imdbId").value(movie.getImdbId()));

    }


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






    //    @Test
//    void getOneMovieByIMDBID_test() throws Exception {
//
//
//        Movie movie = new Movie();
//         movie.getImdbId();
//
////        List<Movie> movies = new ArrayList<>();
////        Movie movie = new Movie();
////        movie.getImdbId();
////        movies.add(movie);
//
//
//        when(movieService.getOneMovieByIMDBID(movie.getImdbId())).thenReturn(movie);
//
//        mockMvc.perform(get(baseUrl).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.imdbId").value(movie.getImdbId()));
//
//
//
//
//
//    }




//   @Test
//    void  searchMovie_ByActor_ByDirector_ByGenre_Test() throws Exception {
//
//        Movie movie = new Movie();
//        List<Movie> movies = new ArrayList<>();
//        movie.setDirectors("Roy");
//        movies.add(movie);
//
//        String searchActor = "Anold";
//        String searchDirector = "Roy";
//        String searchGenre = "comic";
//
//       when(movieService.searchMovieByActor_ByDirector_ByGenre(movie.getDirectors()))
//               .thenReturn(new ArrayList<>()).equals(searchActor);
//
//       mockMvc.perform(get(baseUrl).param("searchActor", searchActor))
//               .andExpect(status().isOk());
//
//   }



}
