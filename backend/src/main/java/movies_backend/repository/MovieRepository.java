package movies_backend.repository;

import movies_backend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findByActor(String actor);

    List<Movie> findByIndustry(String industry);
}
