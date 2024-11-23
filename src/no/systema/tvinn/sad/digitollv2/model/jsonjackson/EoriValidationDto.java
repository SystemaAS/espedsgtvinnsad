package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EoriValidationDto {
	private String eori;
	private String name;
	private Integer status;
	private String statusDescr;
	private String city;
	private String country;
	private String postalCode;
	
}
