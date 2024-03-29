package no.systema.tvinn.sad.manifest.express.util.manager;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;

import no.systema.main.util.DateTimeManager;

public class ManifestDateManager {
	private static final Logger logger = LoggerFactory.getLogger(ManifestDateManager.class.getName());
	
	public String convertToDate_ISO (String value){
		String retval = null;
		
		if(StringUtils.isNotEmpty(value)){
			DateTimeManager dateMgr = new DateTimeManager();
			retval = dateMgr.getDateFormatted_ISO(value, DateTimeManager.NO_FORMAT);
		}
		return retval;
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public String convertToDate_NO (String value){
		DateTimeManager dateMgr = new DateTimeManager();
		return dateMgr.getDateFormatted_NO(value, DateTimeManager.ISO_FORMAT);
	}
}
