package no.systema.tvinn.sad.digitollv2.model.api.routing;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntryActiveTransportMeansDto {
	private String identificationNumber;
	private String typeOfIdentification;
	
}
