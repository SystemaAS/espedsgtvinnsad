package espedsgtvinnsad;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import no.systema.main.util.DateTimeManager;
import no.systema.main.validator.DateValidator;

public class DateTester {

	public static void main(String[] args) throws Exception {
		/*SimpleDateFormat formater=new SimpleDateFormat("yyyyMMdd");
		DateValidator validator = new DateValidator();
		System.out.println(validator.validDrivingAgeNorway("011098"));	
		*/
		
		/*
		int _LIMIT_HOURS = 1;
		Calendar calendar = Calendar.getInstance();
	    System.out.println("Current Date = " + calendar.getTime());
	    // Incrementing hours by 5
	    Calendar calendar2 = Calendar.getInstance();
	    calendar2.add(Calendar.HOUR_OF_DAY, +_LIMIT_HOURS);
	    System.out.println("Updated Date = " + calendar2.getTime());
	    System.out.println("hour = " + calendar2.get(Calendar.HOUR_OF_DAY) + calendar2.get(Calendar.MINUTE));
	    int x = calendar.get(Calendar.HOUR);
	    int y = calendar2.get(Calendar.HOUR);
	    System.out.println("Difference A = " + (y-x));
	    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
		Date userTime = dateFormat.parse("1430");
		String currentHour = String.valueOf(calendar2.get(Calendar.HOUR_OF_DAY));
		String currentMinute = String.valueOf(calendar2.get(Calendar.MINUTE));
		Date currentTime = dateFormat.parse(currentHour + currentMinute);

		if (userTime.after(currentTime)){
		    System.out.println("Ahead!");
		}else{
			System.out.println("INVALID!");
		}
	    */
		
		/*
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		String x = dateFormat.format(Calendar.getInstance().getTime());
		Date now = dateFormat.parse(x);
		Date userTime = dateFormat.parse("240920");
		if(now.compareTo(userTime)==0){
			System.out.println("OK!!");
		}
	    */
	    
		
		DateTimeManager mgr = new DateTimeManager();
		System.out.println(mgr.isValidCurrentAndForwardDate("040920", DateTimeManager.NO_FORMAT));
	}

}
