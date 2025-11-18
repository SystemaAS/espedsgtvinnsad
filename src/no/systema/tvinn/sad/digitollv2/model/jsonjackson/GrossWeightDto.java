package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GrossWeightDto {
	
	private Integer grossWeight;
	private Double grossWeightDbl;
	
}
