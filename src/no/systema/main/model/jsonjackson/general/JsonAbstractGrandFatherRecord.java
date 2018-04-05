/**
 * 
 */
package no.systema.main.model.jsonjackson.general;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import no.systema.main.util.NumberFormatterLocaleAware;
//import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.main.util.MainDateFormatter;

/**
 * All subclasses should implement this method in order to handle reflection
 * 
 * Reflection is needed in all JSON-classes that will build a query-string (POST/GET) to the back-end (AS400) JSON routine 
 * ...which will actually be using UrlEncode and key/value pairs (in a usual URL POST/GET manner)
 * 
 * This class manages a JSON-record (usually the child record of every JSON-container.
 * For managing reflection in each JSON-Container class you should refer to that specific implementation [JsonAbstractGrandFatherContainer]
 * 
 * 
 * @author oscardelatorre
 * @date Nov 7, 2013
 * 
 */
public abstract class JsonAbstractGrandFatherRecord {
	protected MainDateFormatter dateFormatter = new MainDateFormatter();
	protected NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware(); //Kilroy was here ;-)
	
	public abstract List<Field> getFields() throws Exception;

	/**
	 * Convenient method when debugging, exposing JSON record values.
	 * 
	 * @return JSON record name and values
	 */
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
