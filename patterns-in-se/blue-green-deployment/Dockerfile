FROM eclipse-temurin:17
VOLUME /tmp
COPY build/libs/*.jar TUMflix.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "/TUMflix.jar"]
