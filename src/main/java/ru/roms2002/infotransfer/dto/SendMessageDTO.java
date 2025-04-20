package ru.roms2002.infotransfer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SendMessageDTO {

	private String email;

	private String token;
}
