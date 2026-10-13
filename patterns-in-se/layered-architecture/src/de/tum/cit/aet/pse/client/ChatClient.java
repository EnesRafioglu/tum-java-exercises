package de.tum.cit.aet.pse.client;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import de.tum.cit.aet.pse.client.applicationlayer.Application;
import de.tum.cit.aet.pse.client.applicationlayer.ApplicationLayerInterface;
import de.tum.cit.aet.pse.client.networklayer.NetworkLayerInterface;
import de.tum.cit.aet.pse.client.networklayer.TcpNetworkLayer;
import de.tum.cit.aet.pse.client.presentationlayer.AesEncryption;
import de.tum.cit.aet.pse.client.presentationlayer.CaesarEncryption;
import de.tum.cit.aet.pse.client.presentationlayer.PresentationLayerInterface;

public class ChatClient {

	private static final String SERVER_HOST = "localhost";
	private static final int SERVER_PORT = 1337;
	private static final String LOGOUT_MESSAGE = ".logout";
	private Thread waitForUserInputThread;
	private String lastMessageReceived;

    private final ApplicationLayerInterface applicationLayer;

	public static void main(String[] args) {
		ChatClient chatClient = new ChatClient(SERVER_HOST, SERVER_PORT);
		chatClient.start();
	}

	public ChatClient(String host, int port) {
		// TODO: Part 2: Instantiate and configure the layers
		// TODO: Part 2: Add and use a CaesarEncryption implementation for the presentation layer.
		// TODO: Part 3: Replace the CaesarEncryption with an AesEncryption implementation in the presentation layer.
		applicationLayer = new Application(this);

		NetworkLayerInterface networkLayer = new TcpNetworkLayer(host, port);
        String KEY = "0123456701234567";
        PresentationLayerInterface presentationLayer = new AesEncryption(KEY);

		applicationLayer.setPresentationLayer(presentationLayer);
		presentationLayer.setApplicationLayer(applicationLayer);
        presentationLayer.setNetworkLayer(networkLayer);
        networkLayer.setPresentationLayer(presentationLayer);
	}

	public void start() {
		applicationLayer.start();
		waitForUserInputThread = new Thread(this::waitForUserInput);
		waitForUserInputThread.start();
		System.out.println("ChatClient started.");
	}

	private void waitForUserInput() {
		try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
			while (!Thread.interrupted()) {
				String outgoingMessage = scanner.nextLine();
				if (LOGOUT_MESSAGE.equals(outgoingMessage)) {
					shutDown();
					return;
				}
				sendMessage(outgoingMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void receiveMessage(String message) {
		lastMessageReceived = message;
        printMessage(message);
	}

	public void sendMessage(String outgoingMessage) {
		applicationLayer.sendMessage(outgoingMessage);
	}

	private void shutDown() {
		applicationLayer.stop();
		waitForUserInputThread.interrupt();
	}

	private static void printMessage(String message) {
		System.out.println(message);
	}


	public ApplicationLayerInterface getApplicationLayer() {
		return applicationLayer;
	}

	public String getLastMessageReceived() {
		return lastMessageReceived;
	}
}
