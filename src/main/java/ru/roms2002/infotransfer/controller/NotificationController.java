package ru.roms2002.infotransfer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Value("${messenger.url}")
	String messengerUrl;

	private final RestClient restClient;

	public NotificationController(RestClient.Builder restClientBuilder) {
		this.restClient = restClientBuilder.build();
	}

	@PostMapping("/blocked")
	public void sendBlockedNotification(@RequestBody Integer userId) {
		restClient.post().uri(messengerUrl + "/notification/blocked")
				.contentType(MediaType.APPLICATION_JSON).body(userId).retrieve().toBodilessEntity();
	}
}
