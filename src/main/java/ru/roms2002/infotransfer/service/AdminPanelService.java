package ru.roms2002.infotransfer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import ru.roms2002.infotransfer.dto.CheckTokenDTO;

@Service
public class AdminPanelService {

	@Value("${adminpanel.address}")
	private String adminpanelURI;

	private final RestClient restClient;

	public AdminPanelService(RestClient.Builder restClientBuilder) {
		this.restClient = restClientBuilder.build();
	}

	public Boolean checkToken(CheckTokenDTO checkTokenDTO) {
		return restClient.post().uri(adminpanelURI + "/api/checktoken").contentType(MediaType.APPLICATION_JSON)
				.body(checkTokenDTO).retrieve().body(Boolean.class);
	}
}
