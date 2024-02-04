package edu.bbte.softeng.ahim2106.spring.adapter.in.web;

import edu.bbte.softeng.ahim2106.spring.application.port.in.MovieService;
import edu.bbte.softeng.ahim2106.spring.application.domain.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.createMovie(movie));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/buy")
    public ResponseEntity<Movie> buy(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.addQuantity(id));
    }

    @PatchMapping("/{id}/sell")
    public ResponseEntity<Movie> sell(@PathVariable Long id) {
        Movie movie = movieService.subtractQuantity(id);
        if (movie == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movie);
    }
}
