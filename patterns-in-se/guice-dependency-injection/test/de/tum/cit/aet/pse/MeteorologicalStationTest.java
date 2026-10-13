package de.tum.cit.aet.pse;

import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.inject.Guice.createInjector;
import static org.easymock.EasyMock.*;

@SuppressWarnings("unused")
class MeteorologicalStationTest {

	private static final int TEST_HUMIDITY = 42;
	private static final int TEST_WINDSPEED = 0;
	private static final int TEST_TEMPERATURE = 13;

	private Injector injector; // Guice Injector
	private MeteorologicalStationController controller; // SUT

	@BeforeEach
	void setUp() {
		injector = createInjector(new TestModule());
		controller = injector.getInstance(MeteorologicalStationController.class);
		// TODO 9: Get an instance of MeteorologicalStationController from the injector
	}


	// TODO 10: Implement the following test case

	/**
	 * Note: In this case, we are testing measure() method of the controller, which makes calls to getTemperatureData(), getWindspeedData(), getHumidityData() of IMeteorologicalSensorArray
	 * => In this test case, only the mock of IMeteorologicalSensorArray is needed
	 */
	@Test
	void testMeasure() {
		// TODO 11: Get an instance of IMeteorologicalSensorArray mock from the injector
		// TODO 12: Specify the expected behavior of the mock collaborator (use the provided return values TEST_HUMIDITY, TEST_WINDSPEED, TEST_TEMPERATURE)
		// TODO 13: Make sure the mocked instance is ready to mock the expected behavior
		// TODO 14: Test the measure() method of the SUT
		// TODO 15: Verify the behavior of the mock instance
		IMeteorologicalSensorArray mock = injector.getInstance(IMeteorologicalSensorArray.class);
		expect(mock.getTemperatureData()).andReturn(TEST_TEMPERATURE);
		expect(mock.getHumidityData()).andReturn(TEST_HUMIDITY);
		expect(mock.getWindspeedData()).andReturn(TEST_WINDSPEED);

		replay(mock);

		controller.measure();

		verify(mock);

	}

}
