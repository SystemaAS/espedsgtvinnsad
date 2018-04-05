package no.systema.main.validator;

import javax.servlet.http.HttpSession;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;


/**
 * 
 * @author oscardelatorre
 *
 */
public class LoginValidator {
	/**
	 * 
	 * @param session
	 * @return
	 */
	public SystemaWebUser getValidUser(HttpSession session){
		SystemaWebUser user = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		if(user!=null){
			/*
			if(user.getName()!=null && user.getEmail()!=null){
				if(user.getName().length()>2 && new EmailValidator().validateEmail(user.getEmail())){
					return user;
				}else{
					return null;
				}
			}else{
				return null;
			}
			*/
			return user;
		}else{
			return null;
		}

	}

}
