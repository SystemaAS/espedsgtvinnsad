package no.systema.tvinn.sad.digitollv2.model.api;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import no.systema.tvinn.sad.digitollv2.model.api.entrymovementroad.EntryMovRoadDto;
import no.systema.tvinn.sad.digitollv2.model.api.routing.EntryRoutingDto;

@Data
/**
 * Container class for all the DTOs that return a json with a list
 * Since this DtoContainer is generic, there will be a price to pay further on when we marshall the JSON-Paylod
 * Refer to the specific mapper to see the extra implementation 
 * 
 * @author oscardelatorre
 * @date aug 2023
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiGenericDtoResponse {
	
	private String user = null;
	private String avd = null;
	private String pro = null;
	private String tdn = "";
	private String lrn = "";
	private String mrn = "";
	private String statusApi = "";
	private String requestMethodApi = "";
	private String requestId = "";
	//
	private String db_st = "";
	private String db_st2 = "";
	private String db_st3 = "";
	private String timestamp = "";
	private String errMsg = "";
	private String errMsgClean = "";
	//transport
	private String etlnrt = "";
	//master
	private String emlnrt = "";
	private String emlnrm = "";
	//house
	private String ehlnrt = "";
	private String ehlnrm = "";
	private String ehlnrh = "";
	//log
	private Integer ellnrt = 0;
	private Integer ellnrm = 0;
	private Integer ellnrh = 0;
	
	
	
	private List<Object> list = new ArrayList<Object>();
	
	//for entry movement road
	private EntryMovRoadDto entryMovementRoad = new EntryMovRoadDto();
	
	//for routing - entry
	private List<EntryRoutingDto> entryList = new ArrayList<EntryRoutingDto>();
	
}
