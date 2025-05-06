package ru.roms2002.infotransfer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import ru.roms2002.infotransfer.dto.ChangeDepartmentDTO;
import ru.roms2002.infotransfer.dto.ChangeRoleDTO;
import ru.roms2002.infotransfer.dto.ChangeStudgroupDTO;

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

	@PostMapping("/new-group")
	public void sendNewGroupNotification(@RequestBody String groupName) {
		restClient.post().uri(messengerUrl + "/notification/new-group")
				.contentType(MediaType.APPLICATION_JSON).body(groupName).retrieve()
				.toBodilessEntity();
	}

	@PostMapping("/delete-user")
	public void sendDeleteUserNotification(@RequestBody Integer userId) {
		restClient.post().uri(messengerUrl + "/notification/delete-user")
				.contentType(MediaType.APPLICATION_JSON).body(userId).retrieve().toBodilessEntity();
	}

	@PostMapping("/change-group")
	public void sendChangeGroupNotification(@RequestBody ChangeStudgroupDTO dto) {
		restClient.post().uri(messengerUrl + "/notification/change-group")
				.contentType(MediaType.APPLICATION_JSON).body(dto).retrieve().toBodilessEntity();
	}

	@PostMapping("/change-role")
	public void sendChangeRoleNotification(@RequestBody ChangeRoleDTO dto) {
		restClient.post().uri(messengerUrl + "/notification/change-role")
				.contentType(MediaType.APPLICATION_JSON).body(dto).retrieve().toBodilessEntity();
	}

	@PostMapping("/change-department")
	public void sendChangeDepartmentNotification(@RequestBody ChangeDepartmentDTO dto) {
		restClient.post().uri(messengerUrl + "/notification/change-department")
				.contentType(MediaType.APPLICATION_JSON).body(dto).retrieve().toBodilessEntity();
	}

	@PostMapping("/delete-group")
	public void sendDeleteGroupNotification(@RequestBody String groupName) {
		restClient.post().uri(messengerUrl + "/notification/delete-group")
				.contentType(MediaType.APPLICATION_JSON).body(groupName).retrieve()
				.toBodilessEntity();
	}
}
