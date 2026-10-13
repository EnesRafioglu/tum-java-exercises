package de.tum.cit.aet.pse.client.applicationlayer;

import de.tum.cit.aet.pse.client.ChatClient;
import de.tum.cit.aet.pse.client.networklayer.NetworkLayerInterface;
import de.tum.cit.aet.pse.client.presentationlayer.PresentationLayerInterface;

public class Application implements ApplicationLayerInterface {

	// TODO: Part 2: Replace with a reference to the presentation layer

	private final ChatClient chatClient;

    private PresentationLayerInterface presentationLayer;

	public Application(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	@Override
	public void start() {
		// TODO: Part 2: Replace with method invocation of the the presentation layer
		presentationLayer.start();
	}

	@Override
	public void stop() {
		// TODO: Part 2: Replace with method invocation of the the presentation layer
		presentationLayer.stop();
	}

	@Override
	public void sendMessage(String message) {
		// TODO: Part 2: Replace with method invocation of the the presentation layer
		presentationLayer.sendMessage(message);
	}

	@Override
	public void receiveMessage(String message) {
		chatClient.receiveMessage(message);
	}

    @Override
    public void setPresentationLayer(PresentationLayerInterface presentationLayer) {
        this.presentationLayer = presentationLayer;
    }

    @Override
    public PresentationLayerInterface getPresentationLayer() {
        return presentationLayer;
    }

    // TODO: Part 2: Replace the reference to network layer with setter and getter for presentation layer.


}
