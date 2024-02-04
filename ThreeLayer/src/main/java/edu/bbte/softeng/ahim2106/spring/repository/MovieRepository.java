package edu.bbte.softeng.ahim2106.spring.repository;

import edu.bbte.softeng.ahim2106.spring.repository.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}