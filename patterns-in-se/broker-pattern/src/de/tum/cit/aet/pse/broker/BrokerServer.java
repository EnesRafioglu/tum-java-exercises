package de.tum.cit.aet.pse.broker;

import org.apache.activemq.broker.BrokerService;

/**
 * BrokerServer - Starts an embedded ActiveMQ broker.
 * This application must be running for the Publisher and Subscriber apps to work.
 */
public final class BrokerServer {
    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String BROKER_NAME = "IoTSmartHomeBroker";

    private BrokerServer() {
        // Private constructor to prevent instantiation
    }

    public static void main(String[] args) {
        System.out.println("Starting IoT Smart Home Broker...");

        BrokerService broker = new BrokerService();

        try {
            // Configure the broker
            broker.setBrokerName(BROKER_NAME);
            broker.addConnector(BROKER_URL);
            broker.setPersistent(false); // In-memory broker for this exercise

            // Start the broker
            broker.start();

            System.out.println("✓ Broker started successfully!");
            System.out.println("  Broker Name: " + BROKER_NAME);
            System.out.println("  Broker URL: " + BROKER_URL);
            System.out.println("  Status: Running");
            System.out.println("\nPress Ctrl+C to stop the broker...");

            // Keep the broker running
            Thread.currentThread().join();

        } catch (Exception e) {
            System.err.println("✗ Failed to start broker: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
