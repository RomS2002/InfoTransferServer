package ru.roms2002.infotransfer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.roms2002.infotransfer.dto.CheckTokenDTO;
import ru.roms2002.infotransfer.dto.TokenStatus;
import ru.roms2002.infotransfer.dto.UserDetailsDTO;
import ru.roms2002.infotransfer.dto.UserInListDTO;
import ru.roms2002.infotransfer.service.AdminPanelService;
import ru.roms2002.infotransfer.service.UserService;

@RestController
public class MainController {

	@Autowired
	private AdminPanelService adminPanelService;

	@Autowired
	private UserService userService;

	@PostMapping("/checktoken")
	public TokenStatus checkTokenStatus(@RequestBody CheckTokenDTO checkTokenDTO) {

		if (adminPanelService.checkToken(checkTokenDTO)) {
			if (userService.findByRegToken(checkTokenDTO.getToken()).isEmpty())
				return TokenStatus.OK;
			else
				return TokenStatus.ALREADY_USED;
		} else
			return TokenStatus.NOT_EXISTS;
	}

	@PostMapping("/getId")
	public Integer getIdByToken(@RequestBody String token) {
		return adminPanelService.getIdByToken(token);
	}

	@GetMapping("/getUserDetails")
	public UserDetailsDTO getUserDetails(@RequestParam Integer id) {
		return adminPanelService.getUserDetailsById(id);
	}

	@GetMapping("/getUsersByLastName")
	public List<UserInListDTO> getUsersByLastName(@RequestParam("last-name") String lastName) {
		return adminPanelService.getUsersByLastName(lastName);
	}
}
