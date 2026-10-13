package de.tum.cit.aet.pse.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("blue")
public class BlueRecommendationService implements RecommendationService {

    @Override
    public List<String> getRecommendations() {
        return List.of("Inception", "The Matrix", "The Shawshank Redemption");
    }
}
