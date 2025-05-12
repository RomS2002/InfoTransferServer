package ru.roms2002.infotransfer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import ru.roms2002.infotransfer.dto.SendMessageDTO;
import ru.roms2002.infotransfer.service.ConfirmEmailService;
import ru.roms2002.infotransfer.util.EmailContext;

@RequestMapping("/mail")
@RestController
public class MailController {

	@Autowired
	private ConfirmEmailService emailService;

	@PostMapping("/send")
	public boolean sendMessage(@RequestBody SendMessageDTO sendMessageDTO) {
		EmailContext email = new EmailContext();
		email.setTo(sendMessageDTO.getEmail());
		email.setTemplateLocation("tokenEmail.html");
		email.setSubject("Ваш регистрационный токен Nefesle");
		email.setContext(Map.of("token", sendMessageDTO.getToken()));
		email.setFrom("noreply@nefesle.ru");
		email.setDiplayFrom("Nefesle");

		try {
			emailService.sendMail(email);
			return true;
		} catch (MessagingException e) {
			return false;
		}
	}
}
