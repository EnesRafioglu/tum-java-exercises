@REM # Compile the project
call ./gradlew clean build
call docker build -t eist-ngrok .
call docker compose up -d

@REM # Build the image

@REM # Fire up the containers
