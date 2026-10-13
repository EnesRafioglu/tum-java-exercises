package de.tum.cit.aet.pse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProfileConfig {

    @Bean
    public List<Movie> canaryRecommendations() {
        ArrayList<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Dune", "Action", 2021));
        movies.add(new Movie("The Godfather", "Crime", 1972));
        movies.add(new Movie("The Dark Knight", "Action", 2008));
        movies.add(new Movie("Forrest Gump", "Drama", 1994));
        movies.add(new Movie("Inception", "Sci-Fi", 2010));
        movies.add(new Movie("Pulp Fiction", "Crime", 1994));
        movies.add(new Movie("Fight Club", "Drama", 1999));
        movies.add(new Movie("The Matrix", "Action", 1999));
        movies.add(new Movie("The Lord of the Rings: The Fellowship of the Ring", "Adventure", 2001));
        movies.add(new Movie("Gladiator", "Action", 2000));

        return movies;
    }
}
