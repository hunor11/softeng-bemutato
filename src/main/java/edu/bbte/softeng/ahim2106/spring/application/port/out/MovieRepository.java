package edu.bbte.softeng.ahim2106.spring.application.port.out;

import edu.bbte.softeng.ahim2106.spring.adapter.out.persistence.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
}
