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

@Profile("stable")
@RestController
@RequestMapping("/api")
public class StableController {

    private final List<Movie> movies;

    public StableController(List<Movie> movies) {
        this.movies = movies;
    }

    @GetMapping("/search/{movieGenre}")
    public ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String movieGenre) {
        List<Movie> foundMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getGenre().equals(movieGenre)) {
                foundMovies.add(movie);
                return new ResponseEntity<>(foundMovies, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(foundMovies, HttpStatus.NOT_FOUND);
    }
}

