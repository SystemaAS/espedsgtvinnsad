package no.systema.tvinn.sad.digitollv2.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.zone.ZoneRules;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;
import no.systema.jservices.common.util.StringUtils;
import no.systema.tvinn.sad.digitollv2.controller.TvinnSadDigitollAjaxController;

@Data
public class DateUtils {
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadDigitollAjaxController.class.getName());
	
	public String sourceMask = null;
	public String targetMask = null;
	
	public DateUtils(String sourceMask, String targetMask) {
		this.sourceMask = sourceMask;
		this.targetMask = targetMask;
	}
	
	public DateUtils() {
		
	}

	/**
	 * Gets a String formatted date
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String getDate(String value){
		String retval = value;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(this.sourceMask);
			Date date = formatter.parse(value);
			DateFormat dateFormat = new SimpleDateFormat(this.targetMask);  
	        retval = dateFormat.format(date);
		}catch(Exception e) {
			logger.error(e.toString());
		}
		return retval;
	}
	
	/**
	 * Instant.now().truncatedTo(ChronoUnit.SECONDS).toString() is giving wrong TimeZone
	 * @return
	 */
	public String getZuluTimeWithoutMilliseconds() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		ZonedDateTime zonedDateTime = ZonedDateTime.now().truncatedTo(ChronoUnit.SECONDS).withZoneSameInstant(ZoneId.of("Europe/Stockholm"));
		String zoneDateString = formatter.format(zonedDateTime);
		
		return zoneDateString;
	}
	public String getZuluTimeWithoutMillisecondsUTC() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		ZonedDateTime zonedDateTime = ZonedDateTime.now().truncatedTo(ChronoUnit.SECONDS).withZoneSameInstant(ZoneId.of("UTC"));
		String zoneDateString = formatter.format(zonedDateTime);
		
		return zoneDateString;
	}
	
	/**
	 * Expecting date: yyyyMMdd, time:HHmmss
	 * 
	 * @param date
	 * @param time
	 * @return
	 */
	public String getZuluTimeWithoutMilliseconds(Integer date, Integer time) {
		String zoneDateString = "";
		try {
			String timeStr = String.valueOf(time);
			if (timeStr!=null && timeStr.length()<6) {
				timeStr = new StringUtils().leadingStringWithNumericFiller(timeStr, 6, "0");
			}
			DateTimeFormatter formatterIn = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			DateTimeFormatter formatterOut = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
			ZonedDateTime zdtWithZoneOffset = ZonedDateTime.parse(String.valueOf(date) + timeStr , formatterIn.withZone(ZoneId.systemDefault()));
			ZonedDateTime zdtInLocalTimeline = zdtWithZoneOffset.withZoneSameInstant(ZoneId.systemDefault());
			
			zoneDateString = formatterOut.format(zdtInLocalTimeline);
			
		}catch(Exception e) {
			//logger.error(LoggerException.doLog(e).toString());
			e.printStackTrace();
		}
		
		return zoneDateString;
	}
	/**
	 * Expects HHmmss as time
	 * @param date
	 * @param time
	 * @return
	 */
	public String getZuluTimeWithoutMillisecondsUTC(Integer date, Integer time) {
		String zoneDateString = "";
		try {
			String timeStr = String.valueOf(time);
			if (timeStr!=null && timeStr.length()<6) {
				timeStr = new StringUtils().leadingStringWithNumericFiller(timeStr, 6, "0");
			}
			
			DateTimeFormatter formatterIn = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			DateTimeFormatter formatterOut = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
			//the local time to convert into UTC
			ZonedDateTime zdtWithZoneOffset = ZonedDateTime.parse(String.valueOf(date) + timeStr , formatterIn.withZone(ZoneId.systemDefault()));
			//the final converted UTC time
			ZonedDateTime zdtInLocalTimeline = zdtWithZoneOffset.withZoneSameInstant(ZoneId.of("UTC"));
			zoneDateString = formatterOut.format(zdtInLocalTimeline);
			
		}catch(Exception e) {
			//logger.error(LoggerException.doLog(e).toString());
			e.printStackTrace();
		}
		
		return zoneDateString;
	}
	/**
	 * Expects HHmm as time
	 * @param date
	 * @param time
	 * @return
	 */
	public String getZuluTimeWithoutMillisecondsUTC_HHmm(Integer date, Integer time) {
		String zoneDateString = "";
		try {
			String timeStr = String.valueOf(time);
			if (timeStr!=null && timeStr.length()<4) {
				timeStr = new StringUtils().leadingStringWithNumericFiller(timeStr, 4, "0");
			}
			
			DateTimeFormatter formatterIn = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
			DateTimeFormatter formatterOut = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
			//the local time to convert into UTC
			ZonedDateTime zdtWithZoneOffset = ZonedDateTime.parse(String.valueOf(date) + timeStr , formatterIn.withZone(ZoneId.systemDefault()));
			//the final converted UTC time
			ZonedDateTime zdtInLocalTimeline = zdtWithZoneOffset.withZoneSameInstant(ZoneId.of("UTC"));
			zoneDateString = formatterOut.format(zdtInLocalTimeline);
			
		}catch(Exception e) {
			//logger.error(LoggerException.doLog(e).toString());
			e.printStackTrace();
		}
		
		return zoneDateString;
	}
	
	/**
	 * Tullverket "CreationTime"
	 * Datum och klockslag då kuvertet skapades. 
	 * Datumet och klockslaget kan vara tidigare än översändningstidpunkten och ersätter inte eventuell tidstämpel som sätts av transportprotokollet. 
	 * CreationTime ska sättas till UTC-tid (alltså svensk normaltid minus en timma respektive minus två timmar vid sommartid)
	 * 
	 * As a general rule put 125 minutes earlier than timestamp to cover both winter and summer time
	 * @return
	 */
	public String getZuluTimeWithoutMillisecondsForCreationTimeTullverket() {
		boolean isDayLightSavings = this.isDayLightSavings();
		//1-timme tillbaka under vintertid
		int minutesAdjustment = 60; 
		//2-timmar tillbaka under sommartid
		if (isDayLightSavings) { minutesAdjustment = 120; }
		//
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		ZonedDateTime zonedDateTime = ZonedDateTime.now().minusMinutes(minutesAdjustment).truncatedTo(ChronoUnit.SECONDS).withZoneSameInstant(ZoneId.of("Europe/Stockholm"));
		String zoneDateString = formatter.format(zonedDateTime);
		
		return zoneDateString;
	}
	
	private boolean isDayLightSavings() {
		ZonedDateTime now = ZonedDateTime.now( ZoneId.of( "Europe/Stockholm" ) );
		ZoneId z = now.getZone();
		ZoneRules zoneRules = z.getRules();
		Boolean isDst = zoneRules.isDaylightSavings( now.toInstant() );
		return isDst;
	}
	/**
	 * 
	 * @param mask
	 * @return
	 */
	public String getCurrentDate_ISO(String mask){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(mask);
		Calendar cal = Calendar.getInstance();
		try{
			retval = dateFormat.format(cal.getTime()); 
		}catch(Exception e){
			//Nothing
		}
		return  retval; 
	}
	public String getCurrentDate_ISO(String mask, int daysFromNow){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(mask);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, daysFromNow);
		try{
			retval = dateFormat.format(cal.getTime()); 
		}catch(Exception e){
			//Nothing
		}
		return  retval; 
	}
}
