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

    @GetMapping("/{title}")
                public List<Movie> getAllMoviesByTitle(@PathVariable String title){
                  return this.movieService.getAllMoviesByTitle(title);

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


    //    @GetMapping("/{id}")
//    public ResponseEntity<ServiceTicket> getTicketById(@PathVariable Long id){
//        Optional<ServiceTicket> ticket = service.getTicket(id);
//        return ticket.isPresent() ? ResponseEntity.ok(ticket.get()) : ResponseEntity.notFound().build();




//    @GetMapping("/search")
//    public List<Movie> searchMovie_ByActor_ByDirector_ByGenre_Test(@RequestParam String searchActor){
//        return movieService.searchMovieByActor_ByDirector_ByGenre(searchActor);
//    }





    }



