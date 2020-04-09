package com.galvanize.controller;

import com.galvanize.entity.Movie;
import com.galvanize.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<Movie> getAllMovies(){
        return this.movieService.getAllMovies();

    }

    @GetMapping("/imdbId/{imdbId}")
    public Movie getOneMovieReviewByimdbId(@PathVariable String imdbId){
        return movieService.findMovieByImdbId(imdbId);
    }




    @GetMapping("/{title}")
                public List<Movie> getAllMoviesByTitle(@PathVariable String title){
                  return this.movieService.getAllMoviesByTitle(title);

        }


     @PatchMapping("/{addMovie}")
     public List <Movie> addStarRating_For_Movie_Test(@PathVariable List<Object> addMovie){
        return movieService.addRatingStarForMovie(addMovie);

     }


    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteMovieById(@PathVariable Long id) {

        boolean success = movieService.deleteMovieById(id);
        if(success) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.badRequest().body("Movie with id" + id + " was not found.");
        }
    }



//    @GetMapping("/imdbid")
//    public Movie  getJokesContaining(@RequestParam String imdbid){
//        return movieService.getMovieByIMDBID(imdbid);
//    }





//    @GetMapping("/search")
//    public List<Movie> searchMovie_ByActor_ByDirector_ByGenre_Test(@RequestParam String searchActor){
//        return movieService.searchMovieByActor_ByDirector_ByGenre(searchActor);
//    }





    }



