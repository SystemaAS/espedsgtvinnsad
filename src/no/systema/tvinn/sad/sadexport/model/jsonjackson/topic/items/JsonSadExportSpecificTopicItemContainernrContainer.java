package no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items;

import java.util.Collection;
import lombok.Data;

@Data
public class JsonSadExportSpecificTopicItemContainernrContainer {
	private String user;
	private String avd;
	private String opd;
	private String lin;
	private String errMsg;
	private Collection<JsonSadExportSpecificTopicItemContainernrRecord> containerlist;
	

}
