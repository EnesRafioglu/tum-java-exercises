package de.tum.cit.aet.pse.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("blue")
class BlueRecommendationControllerTest {

    @Autowired
    private RecommendationController recommendationController;

    @Test
    void returnsCorrectMessage() {
        String response = recommendationController.getRecommendations().getMessage();
        assertThat(response).isEqualTo("Welcome to TUMflix!");
    }
}
