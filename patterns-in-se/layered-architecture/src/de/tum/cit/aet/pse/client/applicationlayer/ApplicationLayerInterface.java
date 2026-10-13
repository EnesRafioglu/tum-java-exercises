package de.tum.cit.aet.pse.client.applicationlayer;

import de.tum.cit.aet.pse.client.networklayer.NetworkLayerInterface;
import de.tum.cit.aet.pse.client.presentationlayer.PresentationLayerInterface;

public interface ApplicationLayerInterface {

	void start();

	void stop();

	void sendMessage(String message);

	void receiveMessage(String message);

	// TODO: Part 2: Replace with setters and getters for presentation layer
	void setPresentationLayer(PresentationLayerInterface presentationLayer);

	PresentationLayerInterface getPresentationLayer();
}
