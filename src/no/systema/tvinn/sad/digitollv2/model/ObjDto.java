package no.systema.tvinn.sad.digitollv2.model;

import java.io.Serializable;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ObjDto implements Serializable {
	private String name;
	private String type;
	
	public ObjDto() {};
	
}
