package de.tum.cit.aet.pse.app;

import de.tum.cit.aet.pse.model.AppStatus;
import de.tum.cit.aet.pse.model.EnvironmentData;

/**
 * LightApp - Controls the light in the smart home based on environment data.
 * Light turns on during night or when sun level is low.
 */
public class LightApp extends App {

    private static final int SUN_LEVEL_THRESHOLD = 30;

    public static void main(String[] args) throws InterruptedException {
        LightApp app = new LightApp();
        app.start();
        // Keep running until interrupted
        Thread.currentThread().join();
        app.stop();
    }

    @Override
    public void onEnvironmentData(EnvironmentData data) {
        // Light control logic: turn on during night or low sun
        boolean lightOn = "night".equalsIgnoreCase(data.getDaytime()) || data.getSunLevel() < SUN_LEVEL_THRESHOLD;
        String status = lightOn ? "ON" : "OFF";

        System.out.println(getAppPrefix() + " Environment: " + data);
        System.out.println(getAppPrefix() + " Light is now: " + status);

        // Publish status to status topic
        AppStatus appStatus = new AppStatus(getAppName(), status);
        publishStatus(appStatus);
    }

    @Override
    protected String getAppName() {
        return "Light";
    }

    @Override
    protected String getAppPrefix() {
        return "💡 [LIGHT]";
    }
}
