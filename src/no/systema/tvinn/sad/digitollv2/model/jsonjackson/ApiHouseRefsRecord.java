package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiHouseRefsRecord {
	private String documentNumber;
	private String type;
	private String documentStatus;
	private List incompleteDocumentationReasonList;
	private String errMsg = "";
	
}
