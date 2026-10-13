package de.tum.cit.aet.pse.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tum.cit.aet.pse.model.EnvironmentData;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * SensorPublisher - Simulates IoT sensors publishing environment data to the broker.
 * Allows user to set daytime, temperature, and sun level via terminal.
 */
public final class SensorPublisher {
    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String ENVIRONMENT_TOPIC = "iot.environment";

    private static final int LOWER_TEMP_LIMIT = -20;
    private static final int UPPER_TEMP_LIMIT = 50;
    private static final int LOWER_SUN_LEVEL = 0;
    private static final int UPPER_SUN_LEVEL = 100;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private SensorPublisher() {
        // Private constructor to prevent instantiation
    }

    public static void main(String[] args) {
        System.out.println("Starting IoT Sensor Publisher...");

        Connection connection = null;

        try {
            // Create connection to the broker
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            connection = connectionFactory.createConnection();
            connection.start();

            System.out.println("✓ Connected to broker at " + BROKER_URL);

            // Create session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create topic
            Topic environmentTopic = session.createTopic(ENVIRONMENT_TOPIC);

            // Create message producer
            MessageProducer producer = session.createProducer(environmentTopic);

            System.out.println("\nPublisher ready!");
            System.out.println("Enter environment data to control your smart home:");

            Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
            boolean running = true;

            while (running) {

                System.out.println("\n--- Enter Environment Data ---");

                // Get daytime
                System.out.print("Daytime (day/night): ");
                String daytime = scanner.nextLine().trim().toLowerCase();
                while (!daytime.equals("day") && !daytime.equals("night")) {
                    System.out.print("Invalid input. Enter 'day' or 'night': ");
                    daytime = scanner.nextLine().trim().toLowerCase();
                }

                // Get temperature
                System.out.print("Temperature (-20 to +50°C): ");
                double temperature = 0;
                boolean validTemp = false;
                while (!validTemp) {
                    try {
                        temperature = Double.parseDouble(scanner.nextLine().trim());
                        if (temperature >= LOWER_TEMP_LIMIT && temperature <= UPPER_TEMP_LIMIT) {
                            validTemp = true;
                        } else {
                            System.out.print("Temperature must be between -20 and +50. Try again: ");
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid number. Enter temperature (-20 to +50): ");
                    }
                }

                // Get sun level
                System.out.print("Sun level (0-100): ");
                int sunLevel = 0;
                boolean validSun = false;
                while (!validSun) {
                    try {
                        sunLevel = Integer.parseInt(scanner.nextLine().trim());
                        if (sunLevel >= LOWER_SUN_LEVEL && sunLevel <= UPPER_SUN_LEVEL) {
                            validSun = true;
                        } else {
                            System.out.print("Sun level must be between 0 and 100. Try again: ");
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid number. Enter sun level (0-100): ");
                    }
                }

                // Create and publish environment data
                EnvironmentData data = new EnvironmentData(daytime, temperature, sunLevel);
                String json = OBJECT_MAPPER.writeValueAsString(data);
                TextMessage message = session.createTextMessage(json);
                producer.send(message);

                System.out.println("\n✓ Published: " + data);

                // Ask if user wants to continue
                System.out.print("\nPublish more data? (y/n): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("n") || response.equals("no")) {
                    running = false;
                    System.out.println("Shutting down publisher...");
                }


            }

            // Cleanup
            producer.close();
            session.close();
            connection.close();

            System.out.println("✓ Publisher stopped.");

        } catch (JMSException e) {
            System.err.println("✗ Error: Unable to connect to broker!");
            System.err.println("  Make sure the BrokerServer is running first.");
            System.err.println("  Details: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("✗ Unexpected error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
