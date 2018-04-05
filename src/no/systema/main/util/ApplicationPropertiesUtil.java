/**
 * 
 */
package no.systema.main.util;

import java.util.*;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.BeansException;


/**
 * Utility class for accessing properties from a given application.properties
 * The application.properties could be loaded from a given absolute path and not necessarily from the classpath (default)
 * In espedsg this file has been placed outside the espedsg.war. Usually in catalina.home at Tomcat. 
 * This demands a specific configuration per installation at the customer machine (which is what we want since other configuration steps will be done at Tomcat catalina.home
 * such as servlet.xml)
 * 
 * This class is loaded by Spring in servlet.xml
 * 
 * 
 * @author oscardelatorre
 *
 */
public class ApplicationPropertiesUtil extends PropertyPlaceholderConfigurer {
	private static Map<String, String> propertiesMap;
    // Default as in PropertyPlaceholderConfigurer
    private int springSystemPropertiesMode = SYSTEM_PROPERTIES_MODE_FALLBACK;

    @Override
    public void setSystemPropertiesMode(int systemPropertiesMode) {
        super.setSystemPropertiesMode(systemPropertiesMode);
        springSystemPropertiesMode = systemPropertiesMode;
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
        super.processProperties(beanFactory, props);
        propertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String valueStr = resolvePlaceholder(keyStr, props, springSystemPropertiesMode);
            propertiesMap.put(keyStr, valueStr);
        }
    }
    
    public static String getProperty(String name) {
    	return propertiesMap.get(name).toString();
    }
}
