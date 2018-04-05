package no.systema.main.util;

import java.lang.reflect.Field;

public class JavaReflectionUtil {
	
	/**
	 * 
	 * @param from
	 * @param to
	 */
	public static void setFields(Object from, Object to) {
        Field[] fields = from.getClass().getDeclaredFields();
        for (Field field : fields) {
        	field.setAccessible(true);
        	try {
                Field fieldFrom = from.getClass().getDeclaredField(field.getName());
                fieldFrom.setAccessible(true);
                //loop over String attributes and nothing else
                if(!field.getName().contains("logger")){
	                Object value = fieldFrom.get(from);
	                //copy ONLY existent values from the source object (in order to respect values of the target object, if any)
	                if(value!=null && !"".equals(value)){
	                	Field fieldTo = to.getClass().getDeclaredField(field.getName());
	                	fieldTo.setAccessible(true);
	                	fieldTo.set(to, value);
	                }
                }
                
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
	
}
