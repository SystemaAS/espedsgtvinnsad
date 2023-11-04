package no.systema.tvinn.sad.digitollv2.model.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntryRoutingResultDto {
	private String id;
	private String routing;
	
}
