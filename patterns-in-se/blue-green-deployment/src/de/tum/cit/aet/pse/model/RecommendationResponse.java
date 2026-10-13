package de.tum.cit.aet.pse.model;

import java.util.List;

public record RecommendationResponse(String message, List<String> recommendations) {

    public String getMessage() {
        return message;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }
}
