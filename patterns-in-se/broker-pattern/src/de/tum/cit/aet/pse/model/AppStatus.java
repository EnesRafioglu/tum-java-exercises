package de.tum.cit.aet.pse.model;

/**
 * Represents the status of a smart home application.
 * This class is serialized to JSON for transmission through the message broker.
 */
public class AppStatus {
    private String appName; // "Light", "Heating", or "Blinds"
    private String status; // Current status (e.g., "ON", "OFF", "OPEN", "CLOSED")
    private long timestamp;

    public AppStatus() {
        // Default constructor for JSON deserialization
    }

    public AppStatus(String appName, String status) {
        this.appName = appName;
        this.status = status;
        this.timestamp = System.currentTimeMillis();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AppStatus{"
                + "appName='"
                + appName + '\''
                + ", status='"
                + status
                + '\''
                + ", timestamp="
                + timestamp
                + '}';
    }
}
