package de.tum.cit.aet.pse;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Profile("canary")
public class CanaryController {

    private final List<Movie> movies;

    public CanaryController(List<Movie> movies) {
        this.movies = movies;
    }

    @GetMapping("/search/{movieGenre}")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable String movieGenre) {
        List<Movie> result = new ArrayList<>();
        for(Movie movie : movies) {
            if(movie.getGenre().equals(movieGenre)) {
                result.add(movie);
            }
        }
       return new ResponseEntity<>(result, result.isEmpty()? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
}
