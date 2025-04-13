package ru.roms2002.infotransfer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = Shape.OBJECT)
public enum TokenStatus {
	NOT_EXISTS("NOT EXISTS"),
	ALREADY_USED("ALREADY USED"),
	OK("OK");
	
	@JsonProperty("token_status")
	private String tokenStatus;
	
	private TokenStatus(String status) {
		this.tokenStatus = status;
	}
}
