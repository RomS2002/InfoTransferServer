package ru.roms2002.infotransfer.util;

import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailContext {

	private String to;
	private String from;
	private String diplayFrom;
	private String subject;
	private String attachment;
	private String templateLocation;
	private Map<String, Object> context;
}
