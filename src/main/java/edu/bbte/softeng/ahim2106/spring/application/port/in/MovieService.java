package edu.bbte.softeng.ahim2106.spring.application.port.in;

import edu.bbte.softeng.ahim2106.spring.application.domain.entity.Movie;

import java.util.List;

public interface MovieService {
    Movie createMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    void deleteMovieById(Long id);
    Movie addQuantity(Long id);
    Movie subtractQuantity(Long id);
}
