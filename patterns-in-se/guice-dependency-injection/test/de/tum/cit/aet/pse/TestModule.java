package de.tum.cit.aet.pse;

import com.google.inject.AbstractModule;
import org.easymock.EasyMock;

public class TestModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(IMeteorologicalStationGUI.class).toInstance(EasyMock.createMock(IMeteorologicalStationGUI.class));
        bind(IMeteorologicalFileStorage.class).toInstance(EasyMock.createMock(IMeteorologicalFileStorage.class));
        bind(IMeteorologicalSensorArray.class).toInstance(EasyMock.createMock(IMeteorologicalSensorArray.class));
    }
}
