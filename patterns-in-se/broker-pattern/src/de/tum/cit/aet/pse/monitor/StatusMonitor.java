package de.tum.cit.aet.pse.monitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tum.cit.aet.pse.model.AppStatus;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * StatusMonitor - Monitors and displays status updates from all smart home apps.
 * This app subscribes to the status topic and displays status updates in real-time.
 */
public final class StatusMonitor {
    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String STATUS_TOPIC = "iot.app.status";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private StatusMonitor() {
        // Private constructor to prevent instantiation
    }

    private static void handleStatusMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                // TODO: Deserialize JSON message to AppStatus
                //  Print the app name, status and timestamp in a user-friendly format
                String json = ((TextMessage) message).getText();
                AppStatus appStatus = OBJECT_MAPPER.readValue(json, AppStatus.class);
                System.out.println(appStatus.getAppName() + " " + appStatus.getStatus() + " " + appStatus.getTimestamp());
            }
        } catch (Exception e) {
            System.err.println("Error processing status message: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting Status Monitor...");

        Connection connection = null;

        try {
            // Create connection to the broker
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            connection = connectionFactory.createConnection();
            connection.start();

            System.out.println("✓ Connected to broker at " + BROKER_URL);

            // Create session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create status topic
            Topic statusTopic = session.createTopic(STATUS_TOPIC);

            // Create message consumer
            MessageConsumer consumer = session.createConsumer(statusTopic);

            // Set up message listener
            consumer.setMessageListener(StatusMonitor::handleStatusMessage);

            System.out.println("✓ Subscribed to topic: " + STATUS_TOPIC);
            System.out.println("\nMonitoring app status updates... (Press Ctrl+C to stop)\n");

            // Keep the app running
            Thread.currentThread().join();

        } catch (JMSException e) {
            System.err.println("✗ Error: Unable to connect to broker!");
            System.err.println("  Make sure the BrokerServer is running first.");
            System.err.println("  Details: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("✗ Unexpected error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }
}
