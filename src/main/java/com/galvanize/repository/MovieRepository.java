package com.galvanize.repository;


import com.galvanize.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findMovieByImdbId(String imdbId);
//    Optional<Movie> getOneMovieByIMDBID(String imdbid);
//    List<Movie> searchMovieByActor_ByDirector_ByGenre(String searchActor);

}
