

package no.systema.main.util;

import java.util.ResourceBundle;

/**
 * 
 * @author oscardelatorre
 * @obsolete since Feb 10, 2016
 *  Has been replaced by no.systema.main.util.ApplicationPropertiesUtil
 *  
 */
public final class AppResources {
    
    static ResourceBundle rb = ResourceBundle.getBundle("application");
    
    /** Creates a new instance of Resources */
    private AppResources() {
        
    }
    //Default
    public static ResourceBundle getBundle(){
        return rb;
    }
    
}
