package edu.bbte.softeng.ahim2106.spring.service;

import edu.bbte.softeng.ahim2106.spring.repository.MovieRepository;
import edu.bbte.softeng.ahim2106.spring.repository.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) { this.movieRepository = movieRepository; }

    @Override
    public Movie saveMovie(Movie movie) {
        movie.setQuantity(1);
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @Override
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Movie addQuantity(Long id) {
        Movie movieEntity = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        movieEntity.setQuantity(movieEntity.getQuantity() + 1);
        return movieRepository.save(movieEntity);
    }

    @Override
    public Movie subtractQuantity(Long id) {
        Movie movieEntity = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        int newQuantity = movieEntity.getQuantity() - 1;
        if (newQuantity <= 0) {
            movieRepository.deleteById(id);
            return null;
        } else {
            movieEntity.setQuantity(newQuantity);
            return movieRepository.save(movieEntity);
        }
    }
}