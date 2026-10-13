package de.tum.cit.aet.pse.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class RandomConfig {

    @Bean
    public Random deterministicRandom() {
        return new Random(42);
    }
}
