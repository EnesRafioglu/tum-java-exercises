package de.tum.cit.aet.pse;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


public final class DroidFactoryClient {

	private final RestTemplate rest;
	private static final String BASE_URL = "http://localhost:8080/droid/";
	private static final String R_2 = "r2";
	private static final String THREE_PO = "3po";

	public DroidFactoryClient() {
		this.rest = new RestTemplate();
	}

	public void produceR2Async(InboxClient client) {
		var request = createHttpEntity(Droids.R2);
		String result = rest.postForObject(BASE_URL + R_2, request, String.class);
		client.droidReadyR2(result);
	}


	public void produce3POAsync(InboxClient client) {
		var request = createHttpEntity(Droids.THREE_PO);
		String result = rest.postForObject(BASE_URL + THREE_PO, request, String.class);
		client.droidReady3PO(result);
	}

	private HttpEntity<String> createHttpEntity(Droids droid) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<>(Serialization.serializeDroid(droid), headers);
	}


}
