package de.tum.cit.aet.pse.controller;

import de.tum.cit.aet.pse.model.RecommendationResponse;
import de.tum.cit.aet.pse.service.RecommendationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Value("${app.message}")
    private String message;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/api/recommendations")
    public RecommendationResponse getRecommendations() {
        List<String> recommendations = recommendationService.getRecommendations();
        return new RecommendationResponse(message, recommendations);
    }
}
