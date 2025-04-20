package ru.roms2002.infotransfer.service;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import ru.roms2002.infotransfer.util.EmailContext;

@Service
public class ConfirmEmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	public void sendMail(EmailContext email) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		Context context = new Context();
		context.setVariables(email.getContext());
		String emailContent = templateEngine
				.process(email.getTemplateLocation(), context);

		messageHelper.setTo(email.getTo());
		messageHelper.setSubject(email.getSubject());
		messageHelper.setText(emailContent, true);
		try {
			messageHelper.setFrom(email.getFrom(), email.getDiplayFrom());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		emailSender.send(message);
	}
}
