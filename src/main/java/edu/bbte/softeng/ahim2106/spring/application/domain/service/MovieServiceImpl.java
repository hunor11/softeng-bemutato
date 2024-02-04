package edu.bbte.softeng.ahim2106.spring.application.domain.service;

import edu.bbte.softeng.ahim2106.spring.adapter.out.persistence.entities.MovieEntity;
import edu.bbte.softeng.ahim2106.spring.adapter.out.persistence.mapper.MovieMapper;
import edu.bbte.softeng.ahim2106.spring.application.port.in.MovieService;
import edu.bbte.softeng.ahim2106.spring.application.port.out.MovieRepository;
import edu.bbte.softeng.ahim2106.spring.application.domain.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie createMovie(Movie movie) {
        MovieEntity movieEntity = MovieMapper.toEntity(movie);
        movieEntity.setQuantity(1);
        MovieEntity savedMovieEntity = movieRepository.save(movieEntity);
        return MovieMapper.toDomain(savedMovieEntity);
    }

    @Override
    public List<Movie> getAllMovies() {
        List<MovieEntity> movieEntities = movieRepository.findAll();
        return movieEntities.stream()
                .map(MovieMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Movie getMovieById(Long id) {
        MovieEntity movieEntity = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        return MovieMapper.toDomain(movieEntity);
    }

    @Override
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Movie addQuantity(Long id) {
        MovieEntity movieEntity = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        movieEntity.setQuantity(movieEntity.getQuantity() + 1);
        return MovieMapper.toDomain(movieRepository.save(movieEntity));
    }

    @Override
    public Movie subtractQuantity(Long id) {
        MovieEntity movieEntity = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        int newQuantity = movieEntity.getQuantity() - 1;
        if (newQuantity <= 0) {
            movieRepository.deleteById(id);
            return null;
        } else {
            movieEntity.setQuantity(newQuantity);
            return MovieMapper.toDomain(movieRepository.save(movieEntity));
        }
    }
}
