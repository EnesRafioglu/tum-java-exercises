package de.tum.cit.aet.pse.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// TODO 1: Annotate the class with the correct Spring annotation to make it a Spring service bean.
// Annotate the class with the correct Spring annotation to make it active only when the "green" profile is active.
// You may have a look at the BlueRecommendationService class for reference.
@Service
@Profile("green")
public class GreenRecommendationService implements RecommendationService {

    private static final List<String> WINTER_MOVIES = List.of("Frozen", "Home Alone", "The Holiday");
    private static final List<String> SUMMER_MOVIES = List.of("Jaws", "The Parent Trap", "Independence Day");
    private static final List<String> WEEKEND_MOVIES = List.of("Toy Story", "Finding Nemo", "Shrek");
    private static final List<String> WEEKDAY_MOVIES = List.of("Inception", "The Matrix", "The Shawshank Redemption");

    private final Random random;
    private final Clock clock;

    public GreenRecommendationService(Clock clock, Random random) {
        this.clock = clock;
        this.random = random;
    }

    // TODO 2: Implement and override the getRecommendations method to return movie recommendations based on the current month and day of the week.
    public List<String> getRecommendations() {
        LocalDate today = LocalDate.now(clock);
        Month currentMonth = today.getMonth();
        DayOfWeek dayOfWeek = today.getDayOfWeek();

        List<String> recommendations = new ArrayList<>();

        if (currentMonth == Month.DECEMBER || currentMonth == Month.JANUARY || currentMonth == Month.FEBRUARY) {
            recommendations.addAll(WINTER_MOVIES);
        } else if (currentMonth == Month.JUNE || currentMonth == Month.JULY || currentMonth == Month.AUGUST) {
            recommendations.addAll(SUMMER_MOVIES);
        }

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            recommendations.addAll(WEEKEND_MOVIES);
        } else {
            recommendations.addAll(WEEKDAY_MOVIES);
        }


        Collections.shuffle(recommendations, random);

        return recommendations;
    }
}
