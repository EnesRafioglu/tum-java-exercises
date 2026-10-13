package de.tum.cit.aet.pse.client.networklayer;

import de.tum.cit.aet.pse.client.applicationlayer.ApplicationLayerInterface;
import de.tum.cit.aet.pse.client.presentationlayer.PresentationLayerInterface;

public interface NetworkLayerInterface {

	void sendMessage(String message);

	void receiveMessage(String message);

	void openConnection();

	void closeConnection();

	// TODO: Part 2: Replace with setter and getter for presentation layer
    void setPresentationLayer(PresentationLayerInterface presentationLayer);

    PresentationLayerInterface getPresentationLayer();
}
