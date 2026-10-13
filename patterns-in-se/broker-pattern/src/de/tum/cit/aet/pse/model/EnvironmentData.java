package de.tum.cit.aet.pse.model;

/**
 * Represents environment data from sensors.
 * This class is serialized to JSON for transmission through the message broker.
 */
public class EnvironmentData {
    private String daytime; // "day" or "night"
    private double temperature; // -20 to +50 Celsius
    private int sunLevel; // 0-100
    private long timestamp;

    public EnvironmentData() {
        // Default constructor for JSON deserialization
    }

    public EnvironmentData(String daytime, double temperature, int sunLevel) {
        this.daytime = daytime;
        this.temperature = temperature;
        this.sunLevel = sunLevel;
        this.timestamp = System.currentTimeMillis();
    }

    public String getDaytime() {
        return daytime;
    }

    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getSunLevel() {
        return sunLevel;
    }

    public void setSunLevel(int sunLevel) {
        this.sunLevel = sunLevel;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "EnvironmentData{"
                + "daytime='"
                + daytime + '\''
                + ", temperature="
                + temperature
                + ", sunLevel="
                + sunLevel
                + ", timestamp="
                + timestamp
                + '}';
    }
}
