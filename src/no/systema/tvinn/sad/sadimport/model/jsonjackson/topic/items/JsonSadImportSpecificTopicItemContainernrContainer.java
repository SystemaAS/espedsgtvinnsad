package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items;

import java.util.Collection;
import lombok.Data;

@Data
public class JsonSadImportSpecificTopicItemContainernrContainer {
	private String user;
	private String avd;
	private String opd;
	private String lin;
	private String errMsg;
	private Collection<JsonSadImportSpecificTopicItemContainernrRecord> containerlist;
	

}
