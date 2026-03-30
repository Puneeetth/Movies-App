package movies_backend.controller;

import lombok.AllArgsConstructor;
import movies_backend.entity.Movie;
import movies_backend.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies(){
       return movieService.getAllMovies();
    }
    @GetMapping("/actor/{actor}")
    public List<Movie> findByActor(@PathVariable String actor){
        return movieService.findByActor(actor);
    }
    @GetMapping("/industry/{industry}")
    public List<Movie> findByIndustry(@PathVariable String industry){
        return movieService.findByIndustry(industry);
    }
}
