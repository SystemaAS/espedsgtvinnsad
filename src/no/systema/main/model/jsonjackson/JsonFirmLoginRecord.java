package no.systema.main.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;


public class JsonFirmLoginRecord extends JsonAbstractGrandFatherRecord {
	private String fifirm = null; 
	public void setFifirm(String value) {  this.fifirm = value; }
	public String getFifirm() { return this.fifirm;}

	/**
	 * Required for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
}
