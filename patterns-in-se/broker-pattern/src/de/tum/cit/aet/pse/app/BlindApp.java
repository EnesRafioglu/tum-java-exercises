package de.tum.cit.aet.pse.app;

import de.tum.cit.aet.pse.model.AppStatus;
import de.tum.cit.aet.pse.model.EnvironmentData;

/**
 * BlindApp - Controls the blinds in the smart home based on environment data.
 * Blinds close when sun level is high (above 70) to protect from sun.
 */
public class BlindApp extends App {

    // TODO: Create the main method to start the app similar to LightApp
    public static void main(String[] args) {

    }

    private static final int THRESHOLD = 70;

    @Override
    public void onEnvironmentData(EnvironmentData data) {

        // TODO: Implement blind control logic:
        //  1) Evaluate sun level and set the blinds status
        //      Blinds should be "CLOSED" if sun level is above the threshold of 70 else "OPEN"
        //  2) Optional: Print environment and blinds status
        //  3) Publish AppStatus with current app name and status

        String blinds = data.getSunLevel() > THRESHOLD ? "CLOSED" : "OPEN";
        publishStatus(new AppStatus(getAppName(), blinds));
    }

    @Override
    protected String getAppName() {
        return "Blinds";
    }

    @Override
    protected String getAppPrefix() {
        return "🪟 [BLINDS]";
    }
}
