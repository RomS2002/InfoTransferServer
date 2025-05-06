package ru.roms2002.infotransfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import ru.roms2002.infotransfer.dto.CheckTokenDTO;
import ru.roms2002.infotransfer.dto.UserDetailsDTO;
import ru.roms2002.infotransfer.dto.UserInListDTO;

@Service
public class AdminPanelService {

	@Value("${adminpanel.url}")
	private String adminpanelURI;

	private final RestClient restClient;

	public AdminPanelService(RestClient.Builder restClientBuilder) {
		this.restClient = restClientBuilder.build();
	}

	public Boolean checkToken(CheckTokenDTO checkTokenDTO) {
		return restClient.post().uri(adminpanelURI + "/api/checktoken")
				.contentType(MediaType.APPLICATION_JSON).body(checkTokenDTO).retrieve()
				.body(Boolean.class);
	}

	public Integer getIdByToken(String token) {
		return restClient.post().uri(adminpanelURI + "/api/getId").contentType(MediaType.TEXT_PLAIN)
				.body(token).retrieve().body(Integer.class);
	}

	public UserDetailsDTO getUserDetailsById(Integer id) {
		return restClient.get().uri(adminpanelURI + "/api/getUserDetails?id={id}", id).retrieve()
				.body(UserDetailsDTO.class);
	}

	@SuppressWarnings("unchecked")
	public List<UserInListDTO> getUsersByLastName(String lastName) {
		return restClient.get()
				.uri(adminpanelURI + "/api/getUsersByLastName?last-name={lastName}", lastName)
				.retrieve().body(List.class);
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllDepartments() {
		return restClient.get().uri(adminpanelURI + "/api/get-departments").retrieve()
				.body(List.class);
	}
}
