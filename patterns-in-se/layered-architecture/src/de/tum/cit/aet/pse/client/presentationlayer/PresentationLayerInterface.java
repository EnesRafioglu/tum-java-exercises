package de.tum.cit.aet.pse.client.presentationlayer;

import de.tum.cit.aet.pse.client.applicationlayer.ApplicationLayerInterface;
import de.tum.cit.aet.pse.client.networklayer.NetworkLayerInterface;

public interface PresentationLayerInterface {

	// TODO: Part 2: Add setters and getters for neighboring layers. Add other methods according to UML model.

    void start();
    void stop();
    void sendMessage(String message);
    void receiveMessage(String message);
    NetworkLayerInterface getNetworkLayer();
    ApplicationLayerInterface getApplicationLayer();
    void setApplicationLayer(ApplicationLayerInterface applicationLayer);
    void setNetworkLayer(NetworkLayerInterface networkLayer);

}
