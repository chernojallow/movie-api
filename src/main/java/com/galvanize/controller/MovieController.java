package com.galvanize.controller;

import com.galvanize.Movie;
import com.galvanize.MovieService.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    MovieService movieService;

    public MovieController( MovieService movieService){
        this.movieService = movieService;
    }



    @PostMapping
    ResponseEntity<Movie> creatMovie(@RequestBody Movie movie){

        return ResponseEntity.ok(movieService.createMovie(movie));

    }


}
