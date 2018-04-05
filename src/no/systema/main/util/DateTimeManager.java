/**
 * 
 */
package no.systema.main.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Utility class to manage date issues
 * 
 * @author oscardelatorre
 * @date Mar 28, 2014
 * 
 */
public class DateTimeManager {
	public static final String ISO_FORMAT = "yyyyMMdd";
	public static final String NO_FORMAT = "ddMMyy";

	/**
	 * Gets the current ISO date
	 * @return
	 */
	public String getCurrentDate_ISO(){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(ISO_FORMAT);
		Calendar cal = Calendar.getInstance();
		try{
			retval = dateFormat.format(cal.getTime()); 
		}catch(Exception e){
			//Nothing
		}
		return  retval; 
	}
	
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
	
	public String getCurrentYear(){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		try{
			
			retval = dateFormat.format(cal.getTime()); 
			
		}catch(Exception e){
			//Nothing
		}
		
		return  retval; 
	}
	public String getCurrentMonth(){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		Calendar cal = Calendar.getInstance();
		try{
			
			retval = dateFormat.format(cal.getTime()); 
			
		}catch(Exception e){
			//Nothing
		}
		
		return  retval; 
	}
	/**
	 * The method gets a specific month backwards from the current day (today)
	 * @param numberOfMonths (+)=months forward, (-)=months backwards
	 * @return
	 */
	public String getSpecificMonthFrom_CurrentDate_ISO(int numberOfMonths){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(ISO_FORMAT);
		Calendar cal = Calendar.getInstance();
		try{
			cal.add(Calendar.MONTH, numberOfMonths);
			retval = dateFormat.format(cal.getTime()); 
			
		}catch(Exception e){
			//Nothing
		}
		
		return  retval; 
	}
	/**
	 * Gets the current NO date
	 * @return
	 */
	public String getCurrentDate_NO(){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(NO_FORMAT);
		Calendar cal = Calendar.getInstance();
		try{
			retval = dateFormat.format(cal.getTime()); 
		}catch(Exception e){
			//Nothing
		}
		return  retval; 
	}
	
	/**
	 * The method compares with current date and compares it with the user value.
	 * A valid backward time should always return a positive value.
	 * @param userValue
	 * @param dateTimeMask
	 * @return
	 */
	public boolean isValidCurrentAndBackwardDate( String userValue, String dateTimeMask){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat(dateTimeMask);
		try{
			if(userValue!=null && dateTimeMask!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4 && dateTimeMask.length()>=2){
					Date userDate = formatter.parse(userValue);
					Date today = formatter.parse(this.getCurrentDate_ISO());
					if(userDate.equals(today) || userDate.before(today)){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	/**
	 * 
	 * The method compares with current date and compares it with the user value.
	 * A valid forward time should always return a positive value.
	 * @param userValue
	 * @return
	 * 
	 */
	public boolean isValidCurrentAndForwardDate( String userValue, String dateTimeMask){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat(dateTimeMask);
		try{
			if(userValue!=null && dateTimeMask!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4 && dateTimeMask.length()>=2){
					Date userDate = formatter.parse(userValue);
					Date today = formatter.parse(this.getCurrentDate_ISO());
					if(userDate.equals(today) || userDate.after(today)){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	/**
	 * 
	 * The method compares with current date and compares it with the user value.
	 * A valid forward time should always return a positive value.
	 * @param userValue
	 * @return
	 * 
	 */
	public boolean isValidForwardDate( String userValue, String dateTimeMask){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat(dateTimeMask);
		try{
			if(userValue!=null && dateTimeMask!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4 && dateTimeMask.length()>=2){
					Date userDate = formatter.parse(userValue);
					Date today = formatter.parse(this.getCurrentDate_ISO());
					if(userDate.after(today)){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	/**
	 * 
	 * @param userValue
	 * @param dateTimeMask
	 * @return
	 */
	public boolean isValidForwardDateIncludingToday( String userValue, String dateTimeMask){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat(dateTimeMask);
		String currentDate = this.getCurrentDate_ISO();
		if(dateTimeMask!=null && dateTimeMask.length()==6){
			currentDate = this.getCurrentDate_NO();
		}
		try{
			if(userValue!=null && dateTimeMask!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4 && dateTimeMask.length()>=2){
					Date userDate = formatter.parse(userValue);
					Date today = formatter.parse(currentDate);
					if(userDate.equals(today) || userDate.after(today)){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	/**
	 * 
	 * @param userValue
	 * @param dateTimeMask
	 * @return
	 */
	public boolean isValidBackwardDate( String userValue, String dateTimeMask){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat(dateTimeMask);
		try{
			if(userValue!=null && dateTimeMask!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4 && dateTimeMask.length()>=2){
					Date userDate = formatter.parse(userValue);
					Date today = formatter.parse(this.getCurrentDate_ISO());
					if(userDate.before(today)){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	/**
	 * Compares current time with user time
	 * @param userValue
	 * @param dateMask
	 * @return
	 */
	public boolean isValidForwardTime( String userValue, String dateMask){
		boolean retval = false;
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat(dateMask);
			Date userTime = dateFormat.parse(userValue);
			Date currentTime = dateFormat.parse(dateFormat.format(new Date()));

			if (userTime.after(currentTime)){
			    retval = true;
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param userValue
	 * @param dateMask
	 * @return
	 */
	public boolean currentDayBeforeUserDate( String userValue, String dateMask){
		boolean retval = false;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date userDate = formatter.parse(userValue);
			Date today = formatter.parse(this.getCurrentDate_ISO());
			if(today.before(userDate)){
				retval = true;
			}
		}catch(Exception e){
			e.toString();
		}
		return retval;
	}
	
	/**
	 * 
	 * @param value
	 * @param dateMask
	 * @return
	 */
	public String getDateFormatted_ISO(String value, String sourceDateMask){
		String newDateString = value;
		final String OLD_FORMAT = sourceDateMask;
		if(value!=null && !"".equals(value)){
			try{
				String oldDateString = value;
				SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
				Date d = sdf.parse(oldDateString);
				
				sdf.applyPattern(ISO_FORMAT);
				newDateString = sdf.format(d);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return newDateString;
	}
	
	/**
	 * 
	 * @param value
	 * @param dateMask
	 * @return
	 */
	public String getDateFormatted_NO(String value, String sourceDateMask){
		String newDateString = value;
		final String OLD_FORMAT = sourceDateMask;
		if(value!=null && !"".equals(value)){
			try{
				String oldDateString = value;
	
				SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
				Date d = sdf.parse(oldDateString);
				sdf.applyPattern(NO_FORMAT);
				newDateString = sdf.format(d);
				//System.out.println(newDateString);
							
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return newDateString;
	}
	
	/**
	 * User date: 29 = yyyyMM29 (current year and current month) ISO
	 * User date: 2910 = yyy1029 (current year + user month + user day) ISO
	 * 
	 * @param userDate
	 * @return
	 */
	public String adjustUserDateToISODate(String userDate){
		String retval = userDate;
		if(userDate!=null && !"".equals(userDate) && userDate.length()<8){
			if(userDate.length()==2){
				retval = this.getCurrentYear() + this.getCurrentMonth() + userDate;
			}else if (userDate.length()==4){
				String day = userDate.substring(0,2);String month = userDate.substring(2);
				retval = this.getCurrentYear() + month + day;
			}else{
				//nothing
			}
		}
		return retval;
	}

	/**
	 * User time: 23 = 2300
	 * 
	 * @param userTime
	 * @return
	 */
	public String adjustUserTimeToHHmm(String userTime){
		String MINUTES_SUFFIX = "00";
		String HOUR_PREFIX = "0";
		String retval = userTime;
		if(userTime!=null && !"".equals(userTime) && userTime.length()<4){
			if(userTime.length()==2){
				retval = userTime + MINUTES_SUFFIX;
			}else if(userTime.length()==1){
				retval = HOUR_PREFIX + userTime + MINUTES_SUFFIX;
			}else{
				//nothing
			}
		}
		return retval;
	}
	
	/**
	 * 
	 * @param lowerLimitDate
	 * @param lowerLimitDateMask
	 * @param upperLimitDate
	 * @param upperLimitDateMask
	 * @return
	 */
	public boolean validTodayBetweenLimits( String lowerLimitDate, String lowerLimitDateMask, String upperLimitDate, String upperLimitDateMask){
		boolean retval = false;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date today = formatter.parse(this.getCurrentDate_ISO());
			//lower limit date
			SimpleDateFormat formatterLowerLimit = new SimpleDateFormat(lowerLimitDateMask);
			Date lowerDate = formatterLowerLimit.parse(lowerLimitDate);
			
			if(upperLimitDate!=null && !"".equals(upperLimitDate) && !"0".equals(upperLimitDate)){
				SimpleDateFormat formatterUpperLimit = new SimpleDateFormat(upperLimitDateMask);
				Date upperDate = formatterUpperLimit.parse(upperLimitDate);
				if( (today.after(lowerDate) || today.compareTo(lowerDate)==0) && (today.before(upperDate) || today.compareTo(upperDate)==0)){
					retval = true;
				}
			}else{
				//only consider lower limit date
				if(today.after(lowerDate) || today.compareTo(lowerDate)==0){
					retval = true;
				}
			}
			
		}catch(Exception e){
			e.toString();
		}
		return retval;
	}
	/**
	 * 
	 * @param lowerLimitDate
	 * @param lowerLimitDateMask
	 * @return
	 */
	public boolean validTodayBetweenLimits( String lowerLimitDate, String lowerLimitDateMask){
		boolean retval = false;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date today = formatter.parse(this.getCurrentDate_ISO());
			//lower limit date
			SimpleDateFormat formatterLowerLimit = new SimpleDateFormat(lowerLimitDateMask);
			Date lowerDate = formatterLowerLimit.parse(lowerLimitDate);
			
			//only consider lower limit date
			if(today.after(lowerDate) || today.compareTo(lowerDate)==0){
				retval = true;
			}
			
			
			
		}catch(Exception e){
			e.toString();
		}
		return retval;
	}
	/**
	 * Send -10 or 10 if you want to get a new date after a date operation 
	 * @param days
	 * @return
	 */
	public String getNewDateFromNow( int days){
		String retval = "";
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.add( Calendar.DAY_OF_YEAR, days);
		Date daysAgoOrAhead = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		retval = formatter.format(daysAgoOrAhead);
		
		return retval;
	}
	/**
	 * 
	 * @param formatMask
	 * @param days
	 * @return
	 */
	public String getNewDateFromNow( String formatMask, int days){
		String retval = "";
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.add( Calendar.DAY_OF_YEAR, days);
		Date daysAgoOrAhead = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(formatMask);
		retval = formatter.format(daysAgoOrAhead);
		
		return retval;
	}
	
}
