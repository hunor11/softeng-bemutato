package edu.bbte.softeng.ahim2106.spring.adapter.out.persistence.mapper;

import edu.bbte.softeng.ahim2106.spring.application.domain.entity.Movie;
import edu.bbte.softeng.ahim2106.spring.adapter.out.persistence.entities.MovieEntity;

public class MovieMapper {

    public static MovieEntity toEntity(Movie movie) {
        return new MovieEntity(movie.getId(), movie.getTitle(), movie.getDirector(), movie.getQuantity());
    }

    public static Movie toDomain(MovieEntity movieEntity) {
        return new Movie(movieEntity.getId(), movieEntity.getTitle(), movieEntity.getDirector(), movieEntity.getQuantity());
    }
}