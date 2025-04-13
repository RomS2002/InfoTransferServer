package ru.roms2002.infotransfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.roms2002.infotransfer.dto.CheckTokenDTO;
import ru.roms2002.infotransfer.dto.TokenStatus;
import ru.roms2002.infotransfer.service.AdminPanelService;

@RestController
public class MainController {

	@Autowired
	private AdminPanelService adminPanelService;

	@PostMapping("/checktoken")
	public TokenStatus checkTokenStatus(@RequestBody CheckTokenDTO checkTokenDTO) {

		if (adminPanelService.checkToken(checkTokenDTO))
			return TokenStatus.OK;
		else
			return TokenStatus.NOT_EXISTS;
	}
}
