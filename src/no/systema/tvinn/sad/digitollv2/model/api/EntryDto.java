package no.systema.tvinn.sad.digitollv2.model.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntryDto {
	private String entrySummaryDeclarationMRN;
	private EntryTranspDocDto transportDocumentHouseLevel;
	private EntryRoutingResultDto routingResult;
}
