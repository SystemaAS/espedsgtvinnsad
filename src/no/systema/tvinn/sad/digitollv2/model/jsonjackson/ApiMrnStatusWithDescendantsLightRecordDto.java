package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiMrnStatusWithDescendantsLightRecordDto {
	private String documentNumber;
	private String type;
	private String weight;
	private String sumOfWeightForHouseConsignments;
	private String received;
	
	
	private List<HouseConsignments> houseConsignments;
	
}
