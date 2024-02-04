package edu.bbte.softeng.ahim2106.spring.service;

import edu.bbte.softeng.ahim2106.spring.repository.model.Movie;

import java.util.List;

public interface MovieService {
    Movie saveMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    void deleteMovieById(Long id);
    Movie addQuantity(Long id);
    Movie subtractQuantity(Long id);
}
