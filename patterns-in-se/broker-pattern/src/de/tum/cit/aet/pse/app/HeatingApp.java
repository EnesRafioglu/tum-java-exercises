package de.tum.cit.aet.pse.app;

import de.tum.cit.aet.pse.model.AppStatus;
import de.tum.cit.aet.pse.model.EnvironmentData;

/**
 * HeatingApp - Controls the heating in the smart home based on environment data.
 * Heating turns on when temperature is below 18°C.
 */
public class HeatingApp extends App {

    public static void main(String[] args) {

    }
    private static final double THRESHOLD = 18.0;

    @Override
    public void onEnvironmentData(EnvironmentData data) {
        // TODO: Implement heating control logic:
        //  1) Evaluate the temperature and set the heating status and turn
        //      Heating should be "ON" when temperature is below threshold of 18.0, else "OFF"
        //  2) Optional: Log environment and heating status
        //  3) Publish AppStatus with current app name and status

        String heating = data.getTemperature() < THRESHOLD ? "ON" : "OFF";
        publishStatus(new AppStatus(getAppName(), heating));
    }

    @Override
    protected String getAppName() {
        return "Heating";
    }

    @Override
    protected String getAppPrefix() {
        return "🔥 [HEATING]";
    }
}
