package de.tum.cit.aet.pse;

import com.google.inject.Inject;

import java.io.IOException;

public class MeteorologicalStationController {

	private IMeteorologicalStationGUI gui;
	private int currentTemperature;
	private int currentWindspeed;
	private int currentHumidity;
	//TODO 4: Tell Guice to inject this attribute. Make sure to use the newly created interfaces.
	@Inject
	private IMeteorologicalFileStorage storage;
	//TODO 5: Tell Guice to inject this attribute. Make sure to use the newly created interfaces.
	@Inject
	private IMeteorologicalSensorArray sensorArray;

	//TODO 6: Tell Guice to inject the Gui through the constructor
	@Inject
	public MeteorologicalStationController(IMeteorologicalStationGUI gui) {
		this.gui = gui;
	}

	public void measure() {
		currentTemperature = sensorArray.getTemperatureData();
		currentWindspeed = sensorArray.getWindspeedData();
		currentHumidity = sensorArray.getHumidityData();
		gui.displayTemperature(currentTemperature);
		gui.displayWindspeed(currentWindspeed);
		gui.displayHumidity(currentHumidity);
	}

	public void save() throws IOException {
		storage.setTemperature(currentTemperature);
		storage.setWindspeed(currentWindspeed);
		storage.setHumidity(currentHumidity);
		storage.save();
	}
}
