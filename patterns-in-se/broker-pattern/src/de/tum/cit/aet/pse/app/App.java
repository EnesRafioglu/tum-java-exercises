package de.tum.cit.aet.pse.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tum.cit.aet.pse.model.AppStatus;
import de.tum.cit.aet.pse.model.EnvironmentData;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Abstract base class for smart home apps that subscribe to environment data
 * and publish status updates.
 */
public abstract class App {
    protected static final String BROKER_URL = "tcp://localhost:61616";
    protected static final String ENVIRONMENT_TOPIC = "iot.environment";
    protected static final String STATUS_TOPIC = "iot.app.status";

    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private Connection connection;
    private Session session;
    private MessageProducer statusProducer;

    private void handleEnvDataMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                //TODO: read the string from the message and deserialize it into EnvironmentData using
                // objectMapper. Then call onEnvironmentData with the deserialized data.
                String json = ((TextMessage) message).getText();
                EnvironmentData environmentData = OBJECT_MAPPER.readValue(json, EnvironmentData.class);
                onEnvironmentData(environmentData);
            }
        } catch (Exception e) {
            System.err.println("Error processing environment message: " + e.getMessage());
        }
    }

    /**
     * Start the app: connect to broker, create session, subscribe to environment topic.
     */
    public void start() {
        System.out.println("Starting " + getAppName() + "...");

        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            connection = connectionFactory.createConnection();
            connection.start();

            System.out.println("✓ Connected to broker at " + BROKER_URL);

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Topic environmentTopic = session.createTopic(ENVIRONMENT_TOPIC);
            Topic statusTopic = session.createTopic(STATUS_TOPIC);

            statusProducer = session.createProducer(statusTopic);

            MessageConsumer consumer = session.createConsumer(environmentTopic);
            consumer.setMessageListener(this::handleEnvDataMessage);

            System.out.println("✓ Subscribed to topic: " + ENVIRONMENT_TOPIC);
            System.out.println("\nMonitoring environment data for " + getAppName() + "... (Press Ctrl+C to stop)\n");

        } catch (JMSException e) {
            System.err.println("✗ Error: Unable to connect to broker!");
            System.err.println("  Make sure the BrokerServer is running first.");
            System.err.println("  Details: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.err.println("✗ Unexpected error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Publish an AppStatus to the shared status topic.
     */
    public void publishStatus(AppStatus status) {
        // TODO: implement publishStatus:
        //  - ensure `session` and `statusProducer` are initialized (return if not)
        //  - serialize `status` to JSON using `objectMapper`
        //  - using the session, create a `TextMessage` from the JSON and send it via `statusProducer`
        //  - add robust error handling, logging any exceptions to stderr

        if (session == null || statusProducer == null) {
            return;
        }

        try {
            String json = OBJECT_MAPPER.writeValueAsString(status);
            TextMessage textMessage = session.createTextMessage(json);
            statusProducer.send(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stop the app and close the JMS connection.
     */
    public void stop() {
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    /**
     * App-specific handling of incoming environment data. Implementations should
     * perform their control logic and use publishStatus(...) to emit status updates.
     */
    protected abstract void onEnvironmentData(EnvironmentData data);

    /**
     * Readable app name for logging.
     */
    protected abstract String getAppName();

    /**
     * Short prefix used in log messages (e.g. "💡 [LIGHT]").
     */
    protected abstract String getAppPrefix();
}
