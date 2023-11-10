package no.systema.tvinn.sad.digitollv2.model.api.entrymovementroad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntryMovRoadDto {
	
	private Boolean validEntry;
	private String customsOfficeOfEntry;
	private String timeOfEntry;
	private String mrn;
	
}
