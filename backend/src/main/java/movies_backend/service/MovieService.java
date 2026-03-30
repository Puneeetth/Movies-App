package movies_backend.service;

import lombok.AllArgsConstructor;
import movies_backend.entity.Movie;
import movies_backend.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
      return movieRepository.findAll();
    }

        public List<Movie> findByActor(String actor){
          return movieRepository.findByActor(actor);
    }
    public List<Movie> findByIndustry(String industry){
        return movieRepository.findByIndustry(industry);
    }
}
