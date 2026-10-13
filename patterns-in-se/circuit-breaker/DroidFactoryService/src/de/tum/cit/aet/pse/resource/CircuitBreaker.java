package de.tum.cit.aet.pse.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;


@Component
public class CircuitBreaker<T, R> {
    private static final long WAITING_TIME = 10_000_000_000L;
    private static final int MAX_NUMBER_OF_ATTEMPTS_WITH_UNSUCCESSFUL_REPLY = 2;
    //Do NOT make these attributes final, this will cause the tests to fail.
    private Map<T, AtomicInteger> unsuccessfulAttemptsPerEndpoint = new ConcurrentHashMap<>();
    private Map<T, CircuitBreakerState> circuitBreakerStatePerEndpoint = new ConcurrentHashMap<>();
    private Map<T, Long> waitingStartTimePerEndpoint = new ConcurrentHashMap<>();

    public ResponseEntity<R> protectedCall(T object, Function<T, R> operation) {

        CircuitBreakerState state = circuitBreakerStatePerEndpoint.getOrDefault(object, CircuitBreakerState.CLOSED);
        long currentTime = System.nanoTime();
        int unsuccessful = unsuccessfulAttemptsPerEndpoint.getOrDefault(object, new AtomicInteger(0)).get();

        if(state.equals(CircuitBreakerState.OPEN)) {
            boolean waitingTimeHasPassed = (currentTime - waitingStartTimePerEndpoint.getOrDefault(object, 0L)) >= WAITING_TIME;
            if (waitingTimeHasPassed) {
                circuitBreakerStatePerEndpoint.put(object, CircuitBreakerState.HALF_OPEN);
                return protectedCall(object, operation);
            }
             else {
                 return ResponseEntity.internalServerError().build();
            }
        }

        else {
            try {
                unsuccessfulAttemptsPerEndpoint.put(object, new AtomicInteger(0));
                circuitBreakerStatePerEndpoint.put(object, CircuitBreakerState.CLOSED);
                return ResponseEntity.ok(operation.apply(object));
            }
            catch (Exception e) {
                if (state.equals(CircuitBreakerState.HALF_OPEN)) {
                    waitingStartTimePerEndpoint.put(object, currentTime);
                    circuitBreakerStatePerEndpoint.put(object, CircuitBreakerState.OPEN);
                }
                else {
                    unsuccessful++;
                    unsuccessfulAttemptsPerEndpoint.put(object, new AtomicInteger(unsuccessful));
                    if(unsuccessful >= MAX_NUMBER_OF_ATTEMPTS_WITH_UNSUCCESSFUL_REPLY) {
                        circuitBreakerStatePerEndpoint.put(object, CircuitBreakerState.OPEN);
                        waitingStartTimePerEndpoint.put(object, currentTime);
                    }
                }

                throw e;
            }
        }


    }
}
