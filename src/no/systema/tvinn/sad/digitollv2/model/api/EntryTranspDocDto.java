package no.systema.tvinn.sad.digitollv2.model.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntryTranspDocDto {
	private String referenceNumber;
	private String type;
	
}
