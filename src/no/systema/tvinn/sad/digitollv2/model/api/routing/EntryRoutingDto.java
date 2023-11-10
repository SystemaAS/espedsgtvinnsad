package no.systema.tvinn.sad.digitollv2.model.api.routing;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntryRoutingDto {
	private String entrySummaryDeclarationMRN;
	private String estimatedTimeOfArrival;
	private EntryTranspDocDto transportDocumentHouseLevel;
	private EntryActiveTransportMeansDto activeBorderTransportMeans;
	private EntryRoutingResultDto routingResult;
}
