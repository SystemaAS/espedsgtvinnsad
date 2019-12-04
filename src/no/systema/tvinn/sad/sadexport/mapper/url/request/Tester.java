package no.systema.tvinn.sad.sadexport.mapper.url.request;

import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicEurRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicRecord;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
		JsonSadExportTopicEurRecord recordToValidate = new JsonSadExportTopicEurRecord();
		recordToValidate.setEur01a("NAME");
		String urlRequestParamsTopic = urlRequestParameterMapper.getUrlParameterValidString(recordToValidate);
		System.out.println(urlRequestParamsTopic);
	}

}
